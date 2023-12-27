package com.suyh5802.web.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author suyh
 * @since 2023-11-26
 */
@SpringBootApplication
public class TempApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TempApplication.class, args);
//        context.close();
//        System.out.println("######################");
//        SpringApplication.run(TempApplication.class, args);
    }
}
