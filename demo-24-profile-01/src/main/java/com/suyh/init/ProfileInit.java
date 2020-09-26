package com.suyh.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(110)
@Slf4j
public class ProfileInit implements ApplicationRunner {
    @Value("${application.groupId:}")
    private String groupId;

    @Value("${application.artifactId:}")
    private String artifactId;

    @Override
    public void run(ApplicationArguments args) {
        log.info("groupId: {}, artifactId: {}", groupId, artifactId);
    }
}
