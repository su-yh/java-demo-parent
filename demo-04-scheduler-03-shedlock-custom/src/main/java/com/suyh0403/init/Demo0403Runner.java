package com.suyh0403.init;

import com.suyh0403.component.CustomShedlock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class Demo0403Runner implements ApplicationRunner {
    @Resource
    private CustomShedlock customShedlock;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        customShedlock.executeRegistry("abcd");
    }
}
