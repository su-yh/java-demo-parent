package com.suyh6002.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author suyh
 * @since 2023-11-02
 */
@Configuration
public class DemoConfiguration {
    @Bean
    public SuyhSecurityConfiguration suyhSecurityConfiguration() {
        return new SuyhSecurityConfiguration();
    }

    // 如果没有当前这个bean 对象，则会报如下错误：
    // There is no PasswordEncoder mapped for the id "null"
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
