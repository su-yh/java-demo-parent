package com.suyh0304.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SuyhRedisConfiguration {
    @ConfigurationProperties(prefix = "suyh.redis.business")
    @Bean(name = "businessRedisProperties")
    public RedisProperties businesshRedisProperties() {
        return new RedisProperties();
    }

    @ConfigurationProperties(prefix = "suyh.redis.other")
    @Bean(name = "otherRedisProperties")
    public RedisProperties suyhRedisProperties() {
        return new RedisProperties();
    }
}
