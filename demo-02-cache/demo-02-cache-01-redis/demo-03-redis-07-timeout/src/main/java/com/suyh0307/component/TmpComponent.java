package com.suyh0307.component;

import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(cacheNames = "suyh-default", key = "#key01", unless = "#result == null")
    public String getDefault(String key01) {
        System.out.println("getDefault 被调用");

        if (number++ == 0) {
            return null;
        }

        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(nowTime);
    }

    // PT30S: 30S 超时时间 ，以# 分隔
    @Cacheable(cacheNames = "suyh-test-time#PT30S", key = "#key01", unless = "#result == null")
    public String getValue(String key01) {
        System.out.println("getValue 被调用");

        if (number++ == 0) {
            return null;
        }

        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(nowTime);
    }

    @Cacheable(cacheNames = "test1#PT1M", key = "#key01", unless = "#result == null")
    public String getTest1(String key01) {
        System.out.println("getTest1 被调用");

        if (number++ == 0) {
            return null;
        }

        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(nowTime);
    }

    @Cacheable(cacheNames = "test2#PT2M", key = "#key01", unless = "#result == null")
    public String getTest2(String key01) {
        System.out.println("getTest2 被调用");

        if (number++ == 0) {
            return null;
        }

        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(nowTime);
    }
}