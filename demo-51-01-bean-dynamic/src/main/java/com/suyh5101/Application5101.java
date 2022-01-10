package com.suyh5101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application5101 {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(Application5101.class, args);
        SpringContextUtil.setApplicationContext(app);
    }
}
