package com.suyh3303.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自定义spring web 中对jackson 的定义处理
 */
@Component
public class SuyhDemoJacksonBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer {
    @Resource(type = ObjectMapper.class)
    private ObjectMapper objectMapper;

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }
}
