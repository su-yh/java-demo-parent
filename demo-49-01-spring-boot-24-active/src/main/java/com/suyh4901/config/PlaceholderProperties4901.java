package com.suyh4901.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;

@ConfigurationProperties("suyh4901.test")
@Data
public class PlaceholderProperties4901 {
    private String placeholderKey;

    @DeprecatedConfigurationProperty(reason = "nothing")
    public String getPlaceholderKey() {
        return placeholderKey;
    }
}
