package com.suyh.d64.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * @author suyh
 * @since 2024-01-11
 */
public class YamlResourceLoad {
    public static void main(String[] args) {
        // 从类路径加载 YAML 文件
        String classpathYamlPath = "classpath:/suyh_properties.properties";
        Properties classpathProperties = loadYamlProperties(classpathYamlPath);
        printProperties(classpathProperties);

        // 从文件系统加载 YAML 文件
        String filesystemYamlPath = "/suyh/github/java-demo-springboot/demo-64-springboot_properties_yaml/demo64-01-load/src/main/resources/suyh_properties.properties";
        Properties filesystemProperties = loadYamlProperties(filesystemYamlPath);
        printProperties(filesystemProperties);
    }

    private static Properties loadYamlProperties(String yamlFilePath) {
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        Resource resource;

        if (yamlFilePath.startsWith("classpath:")) {
            resource = new ClassPathResource(yamlFilePath.substring("classpath:".length()));
        } else {
            resource = new FileSystemResource(yamlFilePath);
        }

        yamlFactory.setResources(resource);
        return yamlFactory.getObject();
    }

    private static void printProperties(Properties properties) {
        properties.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("-------------");
    }
}
