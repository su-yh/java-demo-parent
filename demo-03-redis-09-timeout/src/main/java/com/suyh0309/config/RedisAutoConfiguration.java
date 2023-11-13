package com.suyh0309.config;

import com.suyh0309.cache.SuyhRedisCacheManager;
import com.suyh0309.cache.SuyhRedisCacheResolver;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching(proxyTargetClass = true)
@Configuration(proxyBeanMethods = false)
public class RedisAutoConfiguration {

//    @Bean
//    public SuyhRedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//        // 配置一个全局性的默认值，最好别漏了。
//        // TODO: suyh - 这里没生效呀？？？
//        //  这里是返回了一个新对象，所以必须要接收一下。
//        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofSeconds(30));
//
//        return new SuyhRedisCacheManager(
//                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
//                redisCacheConfiguration);
//    }

    @Bean
    public SuyhRedisCacheResolver suyhCacheResolver(SuyhRedisCacheManager suyhRedisCacheManager) {
        return new SuyhRedisCacheResolver(suyhRedisCacheManager);
    }
}
