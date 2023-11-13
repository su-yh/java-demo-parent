package com.suyh0308.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2023-11-08
 */
@Component
public class RedisConfiguration extends CachingConfigurerSupport {
    private final CacheManager cacheManager;

    public RedisConfiguration(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public CacheManager cacheManager() {
        return cacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        // TODO: suyh - 似乎使用CacheResolver 就可以 自己解析那个注解了，然后自定义注解，添加缓存有效时间就可以 了。
        // org.springframework.cache.interceptor.AbstractCacheResolver.resolveCaches(..) 这个方法的参数里面就会有元数据信息了。
 //        return new SimpleCacheResolver();
        return super.cacheResolver();
    }
}
