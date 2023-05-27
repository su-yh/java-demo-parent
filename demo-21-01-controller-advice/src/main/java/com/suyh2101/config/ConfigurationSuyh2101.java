package com.suyh2101.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationSuyh2101 {

    // 使用这种方式，显然不太好。我们只需要添加配置项即可。
    // 相关配置项可以查看 JacksonProperties
    // @Bean
    public ObjectMapper customObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
}
