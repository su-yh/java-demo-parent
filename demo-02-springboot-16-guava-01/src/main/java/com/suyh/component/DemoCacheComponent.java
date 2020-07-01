package com.suyh.component;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class DemoCacheComponent {
    private Cache<String, Object> commonCache = null;

    private Map<String, Object> staticDataSource = null;

    @PostConstruct
    public void init() {
        commonCache = CacheBuilder.newBuilder()
                // 设置缓存容器的初始化容量
                .initialCapacity(5)
                // 最大缓存容量，超过后会按照LRU 策略-最近最少使用原则，来移除缓存项
                .maximumSize(10)
                // 写入之后保留1 分钟时间
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build();

        staticDataSource = new HashMap<>();
        staticDataSource.put("suyh-key-1", "suyh-value-1");
        staticDataSource.put("suyh-key-2", "suyh-value-2");
        staticDataSource.put("suyh-key-3", "suyh-value-3");
    }

    public void addCommonCache(String key, Object value) {
        commonCache.put(key, value);
    }

    public Object getCommonCache(String key) {
        Object value = commonCache.getIfPresent(key);
        if (value == null) {
            // 从数据库或者Redis 获取
            value = staticDataSource.get(key);
            if (value != null) {
                addCommonCache(key, value);
            }
        }

        return value;
    }
}
