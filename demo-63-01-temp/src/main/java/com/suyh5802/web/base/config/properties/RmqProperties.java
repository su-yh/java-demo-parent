package com.suyh5802.web.base.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author suyh
 * @since 2024-01-09
 */
@ConfigurationProperties(prefix = RmqProperties.PREFIX)
@Data
public class RmqProperties {
    public static final String PREFIX = "suyh.rmq";

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String virtualHost;
}
