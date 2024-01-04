package com.suyh0201.init;

import com.suyh0201.config.ConfigurationProperties0201;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class App0201Runner implements ApplicationRunner {
    @Resource
    private ConfigurationProperties0201 properties0201;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("configuration properties: {}", properties0201);
    }
}
