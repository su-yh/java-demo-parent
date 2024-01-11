package com.suyh.d64.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author suyh
 * @since 2024-01-11
 */
public class YamlResourceLoad {
    public static void main(String[] args) throws IOException {
        {
            // 从类路径加载 YAML 文件
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            // 这相当于平时我们配置的：classpath:/suyh_properties.properties
            Resource resource = new ClassPathResource("/suyh_properties.properties");
            yamlFactory.setResources(resource);
            Properties classpathProperties = yamlFactory.getObject();
            assert classpathProperties != null;
            printProperties(classpathProperties);
        }

        {
            // 从文件系统加载 YAML 文件
            String yamlFilePath = "/suyh/github/java-demo-springboot/demo-64-springboot_properties_yaml/demo64-01-load/src/main/resources/suyh_properties.properties";

            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            Resource resource = new FileSystemResource(yamlFilePath);

            yamlFactory.setResources(resource);
            Properties filesystemProperties = yamlFactory.getObject();
            assert filesystemProperties != null;
            printProperties(filesystemProperties);
        }

        // ####################################
        // 处理成ResourcePropertySource

        {
            // 从类路径加载 YAML 文件
            // 这相当于平时我们配置的：classpath:/suyh_properties.properties
            Resource resource = new ClassPathResource("/suyh_properties.properties");
            EncodedResource encodedResource = new EncodedResource(resource);
            ResourcePropertySource propertySource = new ResourcePropertySource("name", encodedResource);
            Object propertyValue = propertySource.getProperty("spring.profile.active");
            System.out.println("从类路径加载 propertyValue: " + propertyValue);
        }

        {
            // 从文件系统加载 YAML 文件
            String yamlFilePath = "/suyh/github/java-demo-springboot/demo-64-springboot_properties_yaml/demo64-01-load/src/main/resources/suyh_properties.properties";

            Resource resource = new FileSystemResource(yamlFilePath);
            EncodedResource encodedResource = new EncodedResource(resource);
            ResourcePropertySource propertySource = new ResourcePropertySource("name", encodedResource);
            Object propertyValue = propertySource.getProperty("spring.profile.active");
            System.out.println("从文件系统加载：propertyValue: " + propertyValue);
        }
    }

    private static void printProperties(Properties properties) {
        properties.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("-------------");
    }
}
