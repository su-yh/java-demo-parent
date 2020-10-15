package com.suyh;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application2702 {
    public static void main(String[] args) {
        SpringApplication.run(Application2702.class, args);
    }
}
