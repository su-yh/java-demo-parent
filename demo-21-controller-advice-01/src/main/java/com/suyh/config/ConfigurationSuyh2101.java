package com.suyh.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
@EnableSwagger2
public class ConfigurationSuyh2101 {

    @Bean
    public ObjectMapper customObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        initMapper(mapper);
        return mapper;
    }

    public static void initMapper(ObjectMapper mapper) {
        // 设置默认日期的格式化，优先级低于 @JsonFormat
        mapper.setTimeZone(TimeZone.getDefault());
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
        // 序列化的时候对null 属性进行忽略，所有的null 属性都不会被序列化到json 中。
        // ignored non null field
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 反序列化时,遇到未知属性(那些没有对应的属性来映射的属性,并且没有任何setter或handler来处理这样的属性)时
        // 是否引起结果失败(通过抛JsonMappingException异常).
        // 此项设置只对那些已经尝试过所有的处理方法之后并且属性还是未处理
        // (这里未处理的意思是:最终还是没有一个对应的类属性与此属性进行映射)的未知属性才有影响.
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 允许出现特殊字符和转义符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }
}
