
package com.suyh38.properties;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

/**
 * 这个是JDK 提供的加载属性文件的工具类。
 * 
 * @author sWX5327794
 * @since 2021-05-19
 */
@Slf4j
public class UsageDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(UsageDemo.class.getResourceAsStream("/META-INF/sentinel.api.properties"));
        } catch (IOException e) {
            log.error("load config error", e);
        }
        String redirectPrefix = properties.getProperty("sentinel.api.redirectToV2.enablePrefix", "");
        String[] v1UrlPrefixes = redirectPrefix.split("[,\\s]");
        log.info("v1UrlPrefixes: {}", redirectPrefix);
    }
}
