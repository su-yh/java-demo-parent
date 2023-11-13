package com.suyh0309.cache;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.cache.CacheManager;

import java.util.List;

/**
 * @author suyh
 * @since 2023-11-13
 */
public class SuyhCacheManagerCustomizers extends CacheManagerCustomizers {
    public SuyhCacheManagerCustomizers(List<? extends CacheManagerCustomizer<?>> customizers) {
        super(customizers);
    }

    @Override
    public <T extends CacheManager> T customize(T cacheManager) {
        return super.customize(cacheManager);
    }
}
