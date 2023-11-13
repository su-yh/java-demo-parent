package com.suyh0309.init;

import com.suyh0309.component.TmpComponent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SuyhTestInit implements ApplicationRunner {
    @Resource
    private TmpComponent tmpComponent;

    @Override
    public void run(ApplicationArguments args) {

        for (int i = 0; i < 10; i++) {
            String defaultValue = tmpComponent.getDefault("0");
            String value = "value";
            String valueTest1 = "valueTest1";
            String valueTest2 = "valueTest2";
            System.out.println("value: " + value + ", test1: " + valueTest1 + ", test2: " + valueTest2 + ", defafult: " + defaultValue);
        }
    }
}
