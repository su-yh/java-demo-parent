package com.suyh5802.web.base.config;

import com.suyh5802.web.base.config.properties.RmqProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author suyh
 * @since 2024-01-09
 */
@EnableConfigurationProperties(RmqProperties.class)
@Configuration
public class RmqConfiguration {

}
