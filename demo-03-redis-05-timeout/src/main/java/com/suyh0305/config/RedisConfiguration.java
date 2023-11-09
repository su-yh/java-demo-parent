package com.suyh0305.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author suyh
 * @since 2023-11-08
 */
@EnableCaching(proxyTargetClass = true)
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {
    private final CacheManager cacheManager;

    public RedisConfiguration(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public CacheManager cacheManager() {
        return cacheManager;
    }
}
