package com.suyh.d64.yaml;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 * @author suyh
 * @since 2024-01-12
 */
public class YamlPropertySourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        // YamlPropertySourceLoader
        // 这相当于平时我们配置的：classpath:/suyh_yaml.yaml
        Resource resource = new ClassPathResource("/suyh_yaml.yaml");
        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
        List<PropertySource<?>> propertySourceList = yamlPropertySourceLoader.load("name", resource);
        for (PropertySource<?> propertySource : propertySourceList) {
            Object activeValue = propertySource.getProperty("spring.profile.active");
            System.out.println("activeValue: " + activeValue);
        }
    }
}
