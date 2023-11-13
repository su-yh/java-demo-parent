package com.suyh0309.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private final Map<String, Duration> ttlCacheMap = new ConcurrentHashMap<>();

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        Duration duration = ttlCacheMap.get(name);
        if (duration != null) {
            cacheConfig = cacheConfig.entryTtl(duration);
        }
        return super.createRedisCache(name, cacheConfig);
    }

    /**
     * 用来替代 CacheManager 里面的getCache(name) 方法的，主要是扩展一个自定义注解，并能添加指定的缓存有效时间
     */
    @Override
    public Cache getCache(String name, CacheOperationInvocationContext<?> context) {
        SuyhCacheable suyhCacheable = context.getMethod().getAnnotation(SuyhCacheable.class);

        if (suyhCacheable != null) {
            String timeToLive = suyhCacheable.timeToLive();
            if (StringUtils.hasText(timeToLive)) {
                // 在这里添加，主要是给上面createRedisCache() 使用。具体流程参考 getCache(name) 的 getMissingCache 方法
                ttlCacheMap.computeIfAbsent(name, key -> Duration.parse(timeToLive));
            }
        }

        return getCache(name);
    }
}

