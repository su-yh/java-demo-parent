package com.suyh.component;

import com.suyh.GuavaApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = GuavaApplication.class)
public class GuavaApplicationTest {
    @Resource
    private DemoCacheComponent demoCache;

    @Test
    public void test01() {
        String key = "key-1";
        String value = "value-1";
        demoCache.addCommonCache(key, value);

        Object commonCache = demoCache.getCommonCache(key);
        System.out.println(commonCache);
        Assert.assertNotNull(commonCache);
        Assert.assertEquals("值不等", value, commonCache);

        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 超时之后数据被删除了
        commonCache = demoCache.getCommonCache(key);
        System.out.println(commonCache);
        Assert.assertNull(commonCache);
    }
}
