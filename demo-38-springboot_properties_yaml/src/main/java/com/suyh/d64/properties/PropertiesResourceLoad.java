package com.suyh.d64.properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author suyh
 * @since 2024-01-11
 */
public class PropertiesResourceLoad {
    public static void main(String[] args) {
        String fileLocation = "file:/suyh/github/java-demo-springboot/demo-64-springboot_properties_yaml/demo64-01-load/src/main/resources/suyh_properties.properties";
        String classpathLocation = "classpath:/suyh_properties.properties";
        String defaultLocation = "/suyh_properties.properties";  // 如果没有指定协议则是按classpath 方式加载

        demo01(fileLocation);
        demo01(classpathLocation);
        demo01(defaultLocation);

        demo02(fileLocation, "fileLocation");
        demo02(classpathLocation, "classpathLocation");
        demo02(defaultLocation, "defaultLocation");
    }

    private static void demo01(String filePath) {
        Properties properties;
        try {
            Resource resource = new DefaultResourceLoader().getResource(filePath);
            EncodedResource encodedResource = new EncodedResource(resource);
            properties = PropertiesLoaderUtils.loadProperties(encodedResource);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties", e);
        }

        String propertyValue = properties.getProperty("spring.profile.active");
        System.out.println("propertyValue: " + propertyValue);
    }

    private static void demo02(String location, String name) {
        ResourcePropertySource propertySource;
        try {
            Resource resource = new DefaultResourceLoader().getResource(location);
            EncodedResource encodedResource = new EncodedResource(resource);
            propertySource = new ResourcePropertySource(name, encodedResource);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties", e);
        }

        Object propertyValue = propertySource.getProperty("spring.profile.active");
        System.out.println("propertyValue: " + propertyValue);
    }
}
