package com.suyh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyh.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class SuyhJacksonAutoConfiguration {

    // 这里还是默认关闭吧，其实很少有项目中使用这种配置
    @Bean("objectMapper")
    @ConditionalOnMissingBean(ObjectMapper.class)
    @ConditionalOnProperty(name = "com.suyh.core.objectMapper.enable", havingValue = "true")
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // 这里有个地方需要注意一下，在jackson 2.10 的StdDateFormat 的默认格式化还是使用"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        // 但是在2.11 的时候换成了"yyyy-MM-dd'T'HH:mm:ss.SSSX"就最后一个字母的变动，却会使得无法格式化成功。
        // 它主要的类是在 com.fasterxml.jackson.databind.util.StdDateFormat 类中定义的
        log.info("ObjectMapper configuration: use SuyhJacksonAutoConfiguration");
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        JsonUtils.initMapper(objectMapper);
        return objectMapper;
    }

    /**
     * MappingJackson2HttpMessageConverter 是spring boot 处理@RequestBody 的实体映射处理类
     * 需要验证一下，如果说是正确的那么可以利用派生该类，来实现对空白字符串的统一处理。
     *
     * @param objectMapper
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingMessageConverter httpMessageConverter = new MappingMessageConverter(objectMapper);
        // 设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        httpMessageConverter.setSupportedMediaTypes(list);
        return httpMessageConverter;
    }
}
