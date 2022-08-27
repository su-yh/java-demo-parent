package com.suyh4201;

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
public class ApplicationEnvironmentPreparedEventListener
        implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {
    @Override
    public void onApplicationEvent(@NonNull ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        MutablePropertySources sources = environment.getPropertySources();
        final PropertySource<?> propertySource = sources.get(ConfigCenterLoader.CONFIG_CENTER_NAME);
        System.out.println("propertySource is null: " + (propertySource == null));
        if (propertySource != null) {
            Map<String, Object> configCenterProperties = new HashMap<>();
            configCenterProperties.put("suyh.tmp", 123);
            MapPropertySource mapPropertySource = new MapPropertySource(ConfigCenterLoader.CONFIG_CENTER_NAME, configCenterProperties);

            // 将前面占位的那个属性都替换掉，其实这里可以将新的配置插入在它前面或者后面都可以，不一定要替换掉它。
            sources.replace(ConfigCenterLoader.CONFIG_CENTER_NAME, mapPropertySource);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
