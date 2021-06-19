package com.suyh3903.nacos;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.suyh.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * nacos 配置的监听与缓存做抽象类
 * 实时同步nacos 配置中心的相关配置信息
 * 以每一个应用为维度
 * 每一个应用首次访问时创建相应的监听
 *
 * @since 2021-06-10
 */
@Slf4j
public abstract class AbstractNacosDynamic<T> {

    @Resource
    protected ConfigService configService;

    @Resource(name = "nacosConfigListener")
    protected Executor executor;

    // 并发public 时需要加锁
    private final ReentrantLock reentrantLock = new ReentrantLock();

    // key: appName 以应用为维度，应用首次访问时创建AbstractNacosConfig 实例
    private final Map<String, AbstractNacosConfigListener> nacosConfigMap = new ConcurrentHashMap<>();

    // 往配置中心发布一个最新的配置。
    public void publish(String appName, T configObject) {
        // AssertUtil.notEmpty(appName, "app name cannot be empty");
        if (configObject == null) {
            return;
        }
        AbstractNacosConfigListener nacosConfigInfo = nacosConfigInstance(appName);
        if (nacosConfigInfo == null) {
            log.warn("public failed, appName: {}", appName);
            return;
        }

        nacosConfigInfo.publish(configObject);
    }

    // 获取对应应用在配置中心的配置信息。
    public T getConfigValue(String appName) {
        if (StringUtils.isEmpty(appName)) {
            log.warn("getConfigValue error, appName cannot empty");
            return null;
        }

        AbstractNacosConfigListener nacosConfig = nacosConfigInstance(appName);
        if (nacosConfig == null) {
            log.warn("getConfigValue failed, appName: {}", appName);
            return null;
        }
        return nacosConfig.getConfigObject();
    }

    // 获取一个对应应用的配置监听实例。
    protected AbstractNacosConfigListener nacosConfigInstance(String appName) {
        AbstractNacosConfigListener nacosConfig = nacosConfigMap.get(appName);
        if (nacosConfig == null) {
            // 双重检测锁
            try {
                reentrantLock.lock();
                nacosConfig = nacosConfigMap.get(appName);
                if (nacosConfig == null) {
                    nacosConfig = newInstanceNacosConfig(appName);
                    if (nacosConfig != null) {
                        nacosConfigMap.put(appName, nacosConfig);
                    }
                }
            } catch (NacosException ex) {
                log.error("create AbstractNacosConfigInfo failed, appName: {}", appName);
            } finally {
                reentrantLock.unlock();
            }
        }

        return nacosConfig;
    }

    protected abstract AbstractNacosConfigListener newInstanceNacosConfig(String appName) throws NacosException;

    /**
     * 内部类：每一个应用一个相应nacos 配置监听类对象
     */
    protected abstract class AbstractNacosConfigListener implements Listener {

        protected final String dataId;

        protected final String group;

        // 并发发布配置时需要加锁
        protected final ReentrantLock publicLock = new ReentrantLock();

        public AbstractNacosConfigListener(String dataId, String group) throws NacosException {
            this.dataId = dataId;
            this.group = group;
            init();
        }

        // 首次访问获取配置
        private void init() throws NacosException {
            String configValue = configService.getConfigAndSignListener(dataId, group, 1000, this);
            updateConfigValue(configValue);
        }

        protected abstract void updateConfigValue(String configValue);

        public abstract T getConfigObject();

        /**
         * 获取空值时的相应的字符串，不能为null
         * 一般为json 字符串的空对象
         *
         * @return 空值字符串
         */
        protected abstract String emptyConfigValue();

        public boolean publish(T configObject) {
            try {
                publicLock.lock();
                String configValue = JsonUtil.serializable(configObject);
                if (configValue == null) {
                    configValue = emptyConfigValue();
                }

                configService.publishConfig(dataId, group, configValue);
                return true;
            } catch (NacosException ex) {
                log.error("publish failed, nacos error.", ex);
                return false;
            } finally {
                publicLock.unlock();
            }
        }

        @Override
        public Executor getExecutor() {
            return executor;
        }

        @Override
        public void receiveConfigInfo(String configInfo) {
            updateConfigValue(configInfo);
        }
    }
}
