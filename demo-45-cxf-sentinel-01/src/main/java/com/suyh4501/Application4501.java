package com.suyh4501;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application4501 {
    public static void main(String[] args) {
        SpringApplication.run(Application4501.class, args);

        InitExecutor.doInit();
    }
}
