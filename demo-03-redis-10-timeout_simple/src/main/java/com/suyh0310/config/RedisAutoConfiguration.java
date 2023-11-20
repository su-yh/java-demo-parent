package com.suyh0310.config;

import com.suyh0310.cache.SuyhRedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching(proxyTargetClass = true)
@Configuration(proxyBeanMethods = false)
public class RedisAutoConfiguration {
    @Bean
    public SuyhRedisCacheManagerBuilderCustomizer suyhRedisCacheManagerBuilderCustomizer() {
        return new SuyhRedisCacheManagerBuilderCustomizer();
    }
}
