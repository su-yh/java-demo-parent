package com.suyh0309.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 参考自：{@link org.springframework.cache.interceptor.SimpleCacheResolver} 以及 {@link AbstractCacheResolver}
 */
public class SuyhRedisCacheResolver implements CacheResolver, InitializingBean {
	@Nullable
    private SuyhCacheManager cacheManager;

    /**
     * Construct a new {@code SimpleCacheResolver}.
     *
     * @see #setCacheManager
     */
    public SuyhRedisCacheResolver() {
    }

    /**
     * Construct a new {@code SimpleCacheResolver} for the given {@link CacheManager}.
     *
     * @param cacheManager the CacheManager to use
     */
    public SuyhRedisCacheResolver(SuyhCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void setCacheManager(SuyhCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

	@NonNull
	public SuyhCacheManager getCacheManager() {
		Assert.state(this.cacheManager != null, "No CacheManager set");
		return cacheManager;
	}

	protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return context.getOperation().getCacheNames();
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) {
            return Collections.emptyList();
        }
        Collection<Cache> result = new ArrayList<>(cacheNames.size());
        for (String cacheName : cacheNames) {
            // TODO: suyh - 解析器里面最关键的就是这一句，比起原生的实现，只是使用了SuyhCacheManager 的getCache() 方法。
			Cache cache = getCacheManager().getCache(cacheName, context);
            if (cache == null) {
                throw new IllegalArgumentException("Cannot find cache named '" +
                        cacheName + "' for " + context.getOperation());
            }
            result.add(cache);
        }
        return result;
    }

	@Override
	public void afterPropertiesSet()  {
		Assert.notNull(this.cacheManager, "CacheManager is required");
	}
}
