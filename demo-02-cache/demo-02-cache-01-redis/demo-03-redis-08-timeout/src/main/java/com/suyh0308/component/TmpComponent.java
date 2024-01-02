package com.suyh0308.component;

import com.suyh0308.cache.SuyhCacheable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author suyh
 * @since 2023-11-09
 */
@Component
public class TmpComponent {
    private static int number = 0;

    // 注意：按照官方文档，cacheManager 和 cacheResolver 是互斥参数，同时指定两个可能会导致异常。
    // cacheResolver 参数要指定的是bean 的名称
    @SuyhCacheable(cacheNames = "suyh-default", key = "#key01", unless = "#result == null",
            cacheResolver = "suyhCacheResolver", timeToLive = "PT65S")
    public String getDefault(String key01) {
        System.out.println("getDefault 被调用");

        if (number++ == 0) {
            return null;
        }

        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(nowTime);
    }
}