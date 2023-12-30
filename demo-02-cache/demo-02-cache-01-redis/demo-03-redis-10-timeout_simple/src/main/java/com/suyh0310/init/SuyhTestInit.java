package com.suyh0310.init;

import com.suyh0310.component.TmpComponent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SuyhTestInit implements ApplicationRunner {
    @Resource
    private TmpComponent tmpComponent;
    @Resource
    private CacheProperties cacheProperties;

    @Override
    public void run(ApplicationArguments args) {

        for (int i = 0; i < 10; i++) {
            tmpComponent.getStudent();
            String defaultValue = tmpComponent.getDefault("0");
            String value = "value";
            String valueTest1 = "valueTest1";
            String valueTest2 = "valueTest2";
            System.out.println("value: " + value + ", test1: " + valueTest1 + ", test2: " + valueTest2 + ", defafult: " + defaultValue);
        }
    }
}
