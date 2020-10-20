package com.suyh.define3001.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性封装类，将配置文件中的相关属性封装到当前类实例中
 * some.service.prefix 指定前缀的属性
 * some.service.suffix 指定后缀的属性
 */
@ConfigurationProperties("some.service")    // 这个注解需要配置@EnableConfigurationProperties 使用
@Data
public class SomeServiceProperties {
    private String prefix;
    private String suffix;
}
