package com.suyh6006;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author suyh
 * @since 2023-11-02
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class DemoApplication6006 {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication6006.class, args);
    }
}
