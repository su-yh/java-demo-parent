package com.suyh0305.config;

import com.suyh0305.cache.SuyhRedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@EnableCaching(proxyTargetClass = true)
@Configuration(proxyBeanMethods = false)
public class RedisAutoConfiguration {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        /**
         * 这里的代码配置({@link RedisCacheConfiguration)，可以对应到 {@link CacheProperties.Redis} 然后在配置文件中添加
         * 对应的配置类参考 {@link CacheProperties}。
         * 但是这里却拿不到对应的bean 对象，估计是bean 对象扫描的优先级问题。
         */
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存默认的有效时间，全局控制注解 @Cacheable 的默认过期时间。
                .entryTtl(Duration.ofSeconds(30))
                // 这里禁止了null 值，方法如果返回了null，则会抛异常，可以在方法上添加 @Cacheable(unless="#result == null")
                .disableCachingNullValues();
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

    // 上面的方式，直接创建一个CacheManager 是可以实现的，也可以用下面这种方式来处理
    @Bean
    public SuyhRedisCacheManagerBuilderCustomizer suyhRedisCacheManagerBuilderCustomizer() {
        return new SuyhRedisCacheManagerBuilderCustomizer();
    }
}
