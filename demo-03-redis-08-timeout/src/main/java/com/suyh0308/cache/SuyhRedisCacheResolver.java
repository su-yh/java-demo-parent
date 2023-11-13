/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.suyh0308.cache;

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
