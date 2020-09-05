package com.suyh.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 使用 prefix 前缀可以直接获取到配置文件中对应的属性值
 */
@Configuration
@PropertySource(value = "application.properties", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class PropertiesComponent {
    private String name;
    private String version;
    private String groupId;
    private String artifactId;
}
