package com.suyh;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InitApplication {
    public static void main(String[] args) {
        System.out.println("SpringApplication.run() before.");
        ConfigurableApplicationContext ctx = SpringApplication.run(InitApplication.class, args);
        System.out.println("SpringApplication.run() after. ctx: " + ctx);
    }
}
