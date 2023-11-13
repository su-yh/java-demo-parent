package com.suyh0308.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

/**
 * 自定义cacheNames方式 RedisCacheManager 自定义cacheNames方式可以设置过期时间 格式 name#PT30S
 *
 * @author suyh
 * @since 2023-11-11
 */
public class SuyhRedisCacheManager extends RedisCacheManager implements SuyhCacheManager {

    public SuyhRedisCacheManager(
            RedisCacheWriter cacheWriter,
            RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    /**
     * 用来替代 CacheManager 里面的getCache(name) 方法的，主要是扩展一个自定义注解，并能添加指定的缓存有效时间
     */
    @Override
    public Cache getCache(String name, CacheOperationInvocationContext<?> context) {
        SuyhCacheable suyhCacheable = context.getMethod().getAnnotation(SuyhCacheable.class);
        if (suyhCacheable == null) {
            // 不是使用我的自定义注解，那么就是使用spring 原始的Cacheable 注解
            return getCache(name);
        }

        // TODO: suyh - 这里就是处理自己的有效时间，并添加到Cache 中去。
        return getCache(name);
    }
}

