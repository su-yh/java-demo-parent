package com.suyh0308.config;

import com.suyh0308.cache.SuyhRedisCacheManager;
import com.suyh0308.cache.SuyhRedisCacheResolver;
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

    // 当CacheManager 存在，则CacheAutoConfiguration 不会创建，对应的 配置CacheProperties 也就不会生效了，这个bean 也无法使用。
    // 所以还是要想办法直接使用 RedisCacheManager。
    // 参考一下 RedisCacheConfiguration 中的几个自定义扩展接口，看是否有办法处理。
    // 特别是：org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers.customize(..)
    @Bean
    public SuyhRedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        // 配置一个全局性的默认值，最好别漏了。
        // TODO: suyh - 这里没生效呀？？？
        //  这里是返回了一个新对象，所以必须要接收一下。
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofSeconds(30));

        return new SuyhRedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                redisCacheConfiguration);
    }

    @Bean
    public SuyhRedisCacheResolver suyhCacheResolver(SuyhRedisCacheManager suyhRedisCacheManager) {
        return new SuyhRedisCacheResolver(suyhRedisCacheManager);
    }
}
