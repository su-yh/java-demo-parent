package com.suyh0305.cache;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.time.Duration;

/**
 * 扩展RedisCacheManager 的builder
 * 但是这个是来源是 org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration#cacheManager(org.springframework.boot.autoconfigure.cache.CacheProperties, org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers, org.springframework.beans.factory.ObjectProvider, org.springframework.beans.factory.ObjectProvider, org.springframework.data.redis.connection.RedisConnectionFactory, org.springframework.core.io.ResourceLoader)
 * 如果自定义了CacheManager 则不会走这个自定义的实现
 * 同时也需要将该对象添加到spring 的bean 缓存中，让spring 来进行管理。
 * RedisCacheConfiguration 是通过bean 对象的方式来获取该自定义配置的。
 *
 * @author suyh
 * @since 2023-11-13
 */
public class SuyhRedisCacheManagerBuilderCustomizer implements RedisCacheManagerBuilderCustomizer {
    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
        /**
         * 这里的代码配置({@link RedisCacheConfiguration )，可以对应到 {@link CacheProperties.Redis} 然后在配置文件中添加
         * 对应的配置类参考 {@link CacheProperties}。
         * 但是这里却拿不到对应的bean 对象，估计是bean 对象扫描的优先级问题。
         */
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存默认的有效时间，全局控制注解 @Cacheable 的默认过期时间。
                .entryTtl(Duration.ofSeconds(30))
                // 这里禁止了null 值，方法如果返回了null，则会抛异常，可以在方法上添加 @Cacheable(unless="#result == null")
                .disableCachingNullValues();
        builder.cacheDefaults(redisCacheConfiguration);
    }
}
