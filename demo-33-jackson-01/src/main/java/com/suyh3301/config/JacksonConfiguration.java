package com.suyh3301.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyh3301.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@Slf4j
public class JacksonConfiguration {

    @Bean("objectMapper")
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        log.info("ObjectMapper configuration: use JacksonConfiguration");
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 使用core中的配置进行jackson设置
        JsonUtil.initMapper(objectMapper);
        return objectMapper;
    }
}
