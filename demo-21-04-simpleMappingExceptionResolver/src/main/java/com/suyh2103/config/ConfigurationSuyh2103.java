package com.suyh2103.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;

@Configuration
@EnableSwagger2
public class ConfigurationSuyh2103 {

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        exceptionResolver.setDefaultErrorView("error");
        exceptionResolver.setExceptionAttribute("ex");
        exceptionResolver.setDefaultStatusCode(500);

        // statusCodes 中的key 要与exceptionMappings 的value 完全匹配，才会返回这里的错误码。
        // 但是这里的key，是一个视图，似乎需要我们自己去实现这个页面才可以，否则将会出现404 找不到的结果。
        Properties statusCodeProperties = new Properties();
        statusCodeProperties.setProperty("/error", HttpStatus.NOT_IMPLEMENTED.value() + "");
        statusCodeProperties.setProperty("/error", HttpStatus.NOT_IMPLEMENTED.value() + "");
        // 这里要给的Key, 是匹配的示例字符串。value 是整数类型的字符串
        exceptionResolver.setStatusCodes(statusCodeProperties);

        Properties exceptionProperties = new Properties();
        // 这里的error 将会被statusCode 的error 值匹配到，并拿到500 的错误码。
        exceptionProperties.setProperty("ServletException", "/error");
        exceptionProperties.setProperty("RuntimeException", "/error");
        exceptionResolver.setExceptionMappings(exceptionProperties);
        // 可以通过如下接口添加要排除的异常
        // simpleMappingExceptionResolver.setExcludedExceptions(RuntimeException.class);
        return exceptionResolver;
    }
}
