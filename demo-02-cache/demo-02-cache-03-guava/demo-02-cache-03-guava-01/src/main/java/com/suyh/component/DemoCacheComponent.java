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

    /**
     * 该注解可以实现在运行工程时，自动运行该注解下的方法；
     * 是在项目启动的时候执行这个方法，也可以理解为在spring容器启动的时候执行，可作为一些数据的常规化加载，比如数据字典之类的。
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行
     * 也就是加载顺序
     * 服务器加载Servlet -> servlet 构造函数的加载 -> postConstruct ->init（init是在service 中的初始化方法. 创建service 时发生的事件.
     * ->Service->destory->predestory->服务器卸载serlvet
     */
    @PostConstruct
    public void init() {
        commonCache = CacheBuilder.newBuilder()
                // 设置缓存容器的初始化容量
                .initialCapacity(5)
                // 最大缓存容量，超过后会按照LRU 策略-最近最少使用原则，来移除缓存项
                .maximumSize(10)
                // 写入之后保留时间，指定时间长度与时间单位
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
