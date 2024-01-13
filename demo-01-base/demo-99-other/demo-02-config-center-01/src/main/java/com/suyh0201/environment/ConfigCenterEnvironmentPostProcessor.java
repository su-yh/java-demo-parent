package com.suyh0201.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.NonNull;

import java.util.HashMap;

public class ConfigCenterEnvironmentPostProcessor
        implements EnvironmentPostProcessor, ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {

    /**
     * 由于此时的日志系统还没有初始化，这里使用spring boot 提供的缓存日志。
     * 将日志暂时缓存在该对象中。在后续的合适的时机将这些缓存的日志写到日志文件。
     */
    private static final DeferredLog LOGGER = new DeferredLog();

    @Override
    public int getOrder() {
        // 配置中心的属性配置优先级需要高于本地属性配置
        // return ConfigFileApplicationListener.DEFAULT_ORDER - 2; // springboot 2.4 版本之前
        return ConfigDataEnvironmentPostProcessor.ORDER - 2;
    }

    @Override
    public void onApplicationEvent(@NonNull ApplicationEnvironmentPreparedEvent event) {
        /*
         * 如下的日志代码要生效，则需要它的执行时机在 LoggingApplicationListener.onApplicationEnvironmentPreparedEvent 之后
         * 这里由于order 的值是(Ordered.HIGHEST_PRECEDENCE + 10) 而LoggingApplicationListener.DEFAULT_ORDER是(Ordered.HIGHEST_PRECEDENCE + 20)
         * 使得当前的优先级高于LoggingApplicationListener，所以如果在这里添加则当前日志系统中的相关日志将不会被正常打印出来。
         * 所以这个LOGGER 的日志要通过其他方式处理，或者监听的事件改一个在ApplicationEnvironmentPreparedEvent 之后的，又或者另外实现一个监听器
         * 在那个监听器里面实现这个LOGGER。
         */
        // 在事件监听发生时，将日志写到日志文件中。
        LOGGER.replayTo(ConfigCenterEnvironmentPostProcessor.class);
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
