package com.suyh4901.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitRunner implements ApplicationRunner {
    @Value("${suyh49.test.key01:NO-KEY-01}")
    private String value01;

    @Value("${suyh49.test.key02:NO-KEY-02}")
    private String value02;

    @Value("${spring.profiles.active:NO-ACTIVE}")
    private String profilesActive;

    @Value("${spring.config.activate.on-profile:NO-PROFILE}")
    private String onProfiles;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("suyh49.test.key01: {}", value01);
        log.info("suyh49.test.key02: {}", value02);
        log.info("spring.profiles.active: {}", profilesActive);
        log.info("spring.config.activate.on-profile: {}", onProfiles);
    }
}
