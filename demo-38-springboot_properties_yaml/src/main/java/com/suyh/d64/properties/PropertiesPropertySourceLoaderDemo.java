package com.suyh.d64.properties;

import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 * @author suyh
 * @since 2024-01-12
 */
public class PropertiesPropertySourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        // 这相当于平时我们配置的：classpath:/suyh_properties.properties
        Resource resource = new ClassPathResource("/suyh_properties.properties");
        PropertiesPropertySourceLoader propertiesPropertySourceLoader = new PropertiesPropertySourceLoader();
        List<PropertySource<?>> propertySourceList = propertiesPropertySourceLoader.load("name", resource);
        for (PropertySource<?> propertySource : propertySourceList) {
            Object activeValue = propertySource.getProperty("spring.profile.active");
            System.out.println("activeValue: " + activeValue);
        }
    }
}
