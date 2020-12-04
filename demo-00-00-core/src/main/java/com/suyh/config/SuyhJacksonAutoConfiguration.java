package com.suyh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyh.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@Slf4j
public class SuyhJacksonAutoConfiguration {

    // 这里还是默认关闭吧，其实很少有项目中使用这种配置
    @Bean("objectMapper")
    @ConditionalOnMissingBean(ObjectMapper.class)
    @ConditionalOnProperty(name = "com.suyh.core.objectMapper.enable", havingValue = "true")
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        log.info("ObjectMapper configuration: use SuyhJacksonAutoConfiguration");
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        JsonUtil.initMapper(objectMapper);
        return objectMapper;
    }
}