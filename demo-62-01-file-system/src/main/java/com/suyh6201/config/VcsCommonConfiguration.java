package com.suyh6201.config;

import com.suyh6201.config.properties.VcsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author suyh
 * @since 2023-11-28
 */
@EnableScheduling
@EnableConfigurationProperties(VcsProperties.class)
@Configuration
public class VcsCommonConfiguration {
}
