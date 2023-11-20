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
    private static int number = 0;

    // 在支持Spring Cache的环境下，对于使用@Cacheable标注的方法，Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，
    // 如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
    // 这个unless 没太明白，但是如果配置了redis 禁止null 值的情况，这里的方法返回null 时，则需要添加如下的unless 参数
    @Cacheable(cacheNames = "suyh-test-time", key = "#key01", unless = "#result == null")
    public String getValue(String key01) {
        System.out.println("getValue 被调用");

        if (number++ == 0) {
            return null;
        }

        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(nowTime);
    }
}
