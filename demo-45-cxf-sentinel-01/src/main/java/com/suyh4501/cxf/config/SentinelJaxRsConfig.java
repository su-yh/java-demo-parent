package com.suyh4501.cxf.config;

import com.alibaba.csp.sentinel.adapter.jaxrs.SentinelJaxRsProviderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SentinelJaxRsConfig {

    @Bean
    public SentinelJaxRsProviderFilter sentinelJaxRsProviderFilter() {
        return new SentinelJaxRsProviderFilter();
    }
}
