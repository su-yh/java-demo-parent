package com.suyh4201;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 环境后置处理器
 * 用于处理系统变量与环境变量加载之后，在配置属性文件加载之前处理配置中心的配置属性值加载。
 * 注意：在该流程位置日志还没有初始化，所以还无法使用日志系统打印日志。
 * 不过可以通过抛异常的形式，将错误信息包装在异常消息中。
 *
 * @since 2021-06-18
 */
public class ConfigCenterSourcePlaceholder implements EnvironmentPostProcessor, Ordered {

    // 在这里还没有日志可用，但是可以使用该对象来处理相关日志，在日志初始化之后，它会将这些日志打印出来。
    private static final DeferredLog logger = new DeferredLog();

    public static final String SOURCE_PLACEHOLDER = "sourcePlaceholder";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> configCenterProperties = buildMap();
        MutablePropertySources sources = environment.getPropertySources();
        if (sources.contains(SOURCE_PLACEHOLDER)) {
            throw new RuntimeException("duplicate config property source name " + SOURCE_PLACEHOLDER);
        }
        // 现在前面已经加载了系统变量，环境变量，命令行参数的相关配置属性到环境中了。
        // 为配置中心的配置属性资源占位。后面会从配置中心拉取相关数据然后替换该位置上的所有配置属性。
        // 即： 这里的configCenterProperties 是没有任何意义的。
        sources.addLast(new MapPropertySource(SOURCE_PLACEHOLDER, configCenterProperties));

        if (logger.isDebugEnabled()) {
            logger.debug("config center value.");
        }
        
        // 将日志缓存绑定到ConfigCenterSourcePlaceholder 中，在后续日志系统启动之后打印出来。
        logger.replayTo(ConfigCenterSourcePlaceholder.class);
    }

    private Map<String, Object> buildMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("suyh.env.key", "suyhEnvValue");
        return map;
    }

    @Override
    public int getOrder() {
        // 在配置文件加载之前，这个也很重要，它指定了要在应用配置文件加载之前执行。
        // 因为该监听事件的所有功能都是一起做的，但是会按排序的先后顺序开始。
        // 而配置文件的名称并不固定，所以并不好找到对应的资源名称
        return ConfigDataEnvironmentPostProcessor.ORDER - 2;
        // 低版本的被标记为过时了
        // return ConfigFileApplicationListener.DEFAULT_ORDER - 2;
    }
}
