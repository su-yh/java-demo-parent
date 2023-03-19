package com.suyh2202.config;

import com.suyh2202.vo.Person;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(Person.class)
@Configuration
public class ValidationConfiguration {
}
