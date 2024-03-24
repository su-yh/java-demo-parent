package com.suyh0309.config;

import com.suyh0309.cache.SuyhRedisCacheManager;
import com.suyh0309.cache.SuyhRedisCacheResolver;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

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
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofSeconds(30)).prefixCacheNameWith("suyh-prefix:")
        // 参考：org.springframework.data.redis.cache.CacheKeyPrefix.prefixed()
        //         .computePrefixWith(name -> "suyh-prefix" + name + SEPARATOR)    // 这里可以处理那个尾巴上的双冒号的问题，但是这样处理似乎并不好，还是就按他默认的方式处理就好了，只是连续两个冒号而以没关系。
        //         .serializeValuesWith(null) // 在这里可以指定value 的序列化逻辑   默认使用的是：RedisSerializerToSerializationPairAdapter
                // TODO: suyh - 所以这里如果需要自定义序列化与反序列化的逻辑的话，只需要模仿StringRedisSerializer 的实现即可。
                // .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer.UTF_8))
                // 这里直接使用jackson 的序列化方式就好了，似乎可以直接使用bean 对象呀，但是具体还不清楚。
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer(Object.class)))
        ;

        return new SuyhRedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                redisCacheConfiguration);
    }

    @Bean
    public SuyhRedisCacheResolver suyhCacheResolver(SuyhRedisCacheManager suyhRedisCacheManager) {
        return new SuyhRedisCacheResolver(suyhRedisCacheManager);
    }
}
