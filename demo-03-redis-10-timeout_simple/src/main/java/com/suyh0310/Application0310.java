package com.suyh0310;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application0310 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application0310.class, args);

        System.out.println("结束了");
    }
}
