package com.suyh0201.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

public class ConfigCenterEnvironmentPostProcessor
        implements EnvironmentPostProcessor, Ordered {

    /**
     * 由于此时的日志系统还没有初始化，这里使用spring boot 提供的缓存日志。
     * 将日志暂时缓存在该对象中。在后续的合适的时机将这些缓存的日志写到日志文件。
     */
    public static final DeferredLog LOGGER = new DeferredLog();

    @Override
    public int getOrder() {
        // 配置中心的属性配置优先级需要高于本地属性配置
        // return ConfigFileApplicationListener.DEFAULT_ORDER - 2; // springboot 2.4 版本之前
        return ConfigDataEnvironmentPostProcessor.ORDER - 2;
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // 在启动时，这里首次加载配置中心。将配置中心的值加载下来并放到 environment 的属性源中。
        // 并按顺序放在系统属性源之后。
        MutablePropertySources propertySources = environment.getPropertySources();
        HashMap<String, Object> configProperties = new HashMap<>();
        loadConfigCenter(configProperties);
        // TODO: suyh - nacos 实现了一个NacosPropertySource 有空有可以看一下，nacos 的配置中心是怎么实现的。
        PropertySource<?> configCenter = new MapPropertySource("configCenter", configProperties);
        propertySources.addLast(configCenter);
    }

    private void loadConfigCenter(HashMap<String, Object> configProperties) {
        // 连接配置中心，并加载对应的配置属性值，填充到map 中。
        configProperties.put("suyh-key", "suyh-value");
        LOGGER.info("load config center property.");
        System.out.println("load config center property.");
    }
}
