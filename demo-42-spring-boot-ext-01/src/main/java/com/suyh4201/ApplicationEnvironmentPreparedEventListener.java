package com.suyh4201;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 环境处理完的后置处理器，在环境变量，配置文件(application*.*) 都加载完了之后的处理。
 */
@Slf4j
public class ApplicationEnvironmentPreparedEventListener
        implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {
    private static final String CONFIG_CENTER_NAME = "configCenter";

    @Override
    public void onApplicationEvent(@NonNull ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        // 是否启用配置中心。
        final Boolean enabled = environment.getProperty("suyh.config-center.enabled", Boolean.class);
        if (enabled == null || !enabled) {
            // 如果不配置，默认不加载配置中心
            return;
        }
        MutablePropertySources sources = environment.getPropertySources();
        final PropertySource<?> propertySource = sources.get(ConfigCenterSourcePlaceholder.SOURCE_PLACEHOLDER);
        if (propertySource == null) {
            // 如果前面的占位出了问题，这里也不做处理，认为是个错误。
            log.warn("cannot found property source: {}", ConfigCenterSourcePlaceholder.SOURCE_PLACEHOLDER);
            return;
        }

        Map<String, Object> configCenterProperties = new HashMap<>();
        pullConfigCenterProperties(configCenterProperties);

        MapPropertySource mapPropertySource = new MapPropertySource(CONFIG_CENTER_NAME, configCenterProperties);

        // 将前面占位的那个属性都替换掉，其实这里可以将新的配置插入在它前面或者后面都可以，不一定要替换掉它。
        sources.replace(ConfigCenterSourcePlaceholder.SOURCE_PLACEHOLDER, mapPropertySource);
    }

    /**
     * 从配置中心拉取配置属性
     *
     * @param configCenterProperties 将配置中心拉取到的属性值填充到该变量中。
     */
    private void pullConfigCenterProperties(@NonNull Map<String, Object> configCenterProperties) {
        // TODO: suyh - 从配置中心拉取配置属性
    }

    @Override
    public int getOrder() {
        // 优先级不能太低，万一谁要使用这些配置属性，而取到的不是配置中心的值就不行了。
        // 但是还是留一些空间，万一要做什么其他的操作呢。
        return Ordered.HIGHEST_PRECEDENCE + 100;
    }
}
