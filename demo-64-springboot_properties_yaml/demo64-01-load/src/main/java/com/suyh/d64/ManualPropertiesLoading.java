package com.suyh.d64;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

/**
 * @author suyh
 * @since 2024-01-11
 */
public class ManualPropertiesLoading {

    public static void main(String[] args) {
        String propertiesFilePath = "file:/opt/trend_oper/suyh_properties.properties";
        PropertySource<?> propertySource = loadProperties(propertiesFilePath);

        // Access properties from the loaded PropertySource
        String propertyValue = (String) propertySource.getProperty("rmqPrefetchCount");
        System.out.println("rmqPrefetchCount = " + propertyValue);
    }

    private static PropertySource<?> loadProperties(String propertiesFilePath) {
        try {
            Resource resource = new DefaultResourceLoader().getResource(propertiesFilePath);
            EncodedResource encodedResource = new EncodedResource(resource);
            return new ResourcePropertySource(propertiesFilePath, encodedResource);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties", e);
        }
    }
}
