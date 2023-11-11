package com.suyh0307.config;

import com.suyh0307.cache.SuyhRedisCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@EnableCaching(proxyTargetClass = true)
@Configuration(proxyBeanMethods = false)
public class RedisAutoConfiguration {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        // 配置一个全局性的默认值，最好别漏了。这里没生效呀？？？
        redisCacheConfiguration.entryTtl(Duration.ofSeconds(30));

        return new SuyhRedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                redisCacheConfiguration);
    }
}
