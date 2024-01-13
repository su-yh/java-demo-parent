package com.suyh0201.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2024-01-13
 */
@Component
@Slf4j
public class TempRunner implements ApplicationRunner {
    @Value("${suyh-key}")
    private String value;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("suyh-key: " + value);
    }
}
