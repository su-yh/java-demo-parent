package com.suyh4201;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
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
public class ConfigCenterLoader implements EnvironmentPostProcessor, Ordered {

    /**
     * 配置中心属性名，用于存放在系统属性中。
     */
    public static final String CONFIG_CENTER_NAME = "configCenter";

    // 此属性要生效必须放在命令行参数上
    // 当然，放到系统环境变量中也是可以的。
    public static final String ENABLED_CONFIG_CENTER = "suyh.ext.config.center.enabled";

    private ConfigurableEnvironment environment;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        this.environment = environment;
        // 是否禁止连接到配置中心
        Boolean enabledConfigCenter = environment.getProperty(ENABLED_CONFIG_CENTER, Boolean.class, Boolean.TRUE);
        if (!enabledConfigCenter) {
            return;
        }

        Map<String, Object> configCenterProperties = buildMap();
        MutablePropertySources sources = environment.getPropertySources();
        if (sources.contains(CONFIG_CENTER_NAME)) {
            throw new RuntimeException("duplicate config property source name " + CONFIG_CENTER_NAME);
        }
        // 这里是添加到后面，现在前面应该是有系统变量，环境变量，命令行参数。
        sources.addLast(new MapPropertySource(CONFIG_CENTER_NAME, configCenterProperties));
    }

    private Map<String, Object> buildMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("suyh.env.key", "suyhEnvValue");
        return map;
    }

    @Override
    public int getOrder() {
        // 在配置文件加载之前，这个也很重要，它指定了要在应用配置文件加载之前执行。
        return ConfigFileApplicationListener.DEFAULT_ORDER - 2;
    }
}
