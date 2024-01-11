package com.suyh.d64;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author suyh
 * @since 2024-01-11
 */
public class LoadPropertiesFromAbsolutePath {

    public static void main(String[] args) {
        String absoluteFilePath = "file:/opt/trend_oper/suyh_properties.properties";
        Properties properties = loadProperties(absoluteFilePath);

        // Access properties
        String propertyValue = properties.getProperty("rmqPrefetchCount");
        System.out.println("rmqPrefetchCount = " + propertyValue);
    }

    private static Properties loadProperties(String absoluteFilePath) {
        try {
            Resource resource = new DefaultResourceLoader().getResource(absoluteFilePath);
            EncodedResource encodedResource = new EncodedResource(resource);
            return PropertiesLoaderUtils.loadProperties(encodedResource);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties", e);
        }
    }
}
