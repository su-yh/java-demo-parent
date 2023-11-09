package com.suyh0305.component;

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

    @Cacheable(cacheNames = "suyh-test-time", key = "#key01")
    public String getValue(String key01) {
        System.out.println("getValue 被调用");
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(nowTime);
    }
}
