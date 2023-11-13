package com.suyh0309.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.lang.Nullable;

/**
 * @author suyh
 * @since 2023-11-13
 */
public interface SuyhCacheManager extends CacheManager {
    @Nullable
    Cache getCache(String name, CacheOperationInvocationContext<?> context);
}
