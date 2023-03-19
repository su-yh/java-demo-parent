package com.suyh2202.config;

import com.suyh2202.vo.Person;
import com.suyh2202.vo.PersonPlus;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({Person.class, PersonPlus.class})
@Configuration
public class ValidationConfiguration {
}
