package com.suyh13401;

import com.suyh13401.custom.CustomEndpoint;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Endpoint.class)
public class BeansConfiguration13401 {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnAvailableEndpoint
    public CustomEndpoint customEndpoint() {
        return new CustomEndpoint();
    }
}
