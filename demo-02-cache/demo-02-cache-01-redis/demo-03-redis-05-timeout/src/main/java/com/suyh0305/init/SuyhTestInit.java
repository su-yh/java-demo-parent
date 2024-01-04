package com.suyh0305.init;

import com.suyh0305.component.TmpComponent;
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
            String value = tmpComponent.getValue("0");
            System.out.println("value: " + value);
        }
    }
}
