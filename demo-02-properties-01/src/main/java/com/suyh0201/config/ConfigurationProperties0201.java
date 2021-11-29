package com.suyh0201.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 使用 prefix 前缀可以直接获取到配置文件中对应的属性值
 */
@PropertySource(value = "application.properties", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "application")
@Data
public class ConfigurationProperties0201 {
    private String name;
    private String version;
    private String groupId;
    private String artifactId;
}
