package com.suyh0306.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@EnableCaching(proxyTargetClass = true)
@Configuration(proxyBeanMethods = false)
public class RedisAutoConfiguration {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存有效期，默认时间为30S
                .entryTtl(Duration.ofSeconds(30));

        // 配置缓存过期时间
        Map<String, RedisCacheConfiguration> cacheNameMap = new HashMap<>();
        // 设置test1缓存空间有效期1分钟
        cacheNameMap.put("test1", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(1)));
        // 设置test2缓存空间有效期2分钟
        cacheNameMap.put("test2", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)));

        Set<String> cacheNames = cacheNameMap.keySet();

        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                // 设置初始化缓存空间
                .initialCacheNames(cacheNames)
                // 加载配置
                .withInitialCacheConfigurations(cacheNameMap)
                .cacheDefaults(redisCacheConfiguration).build();
    }
}
