package com.suyh4401.config;

import com.suyh4401.method.annotation.RequestParamMethodPropertiesBodyArgumentConvertObjectResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {
    /**
     * 完了之后把这个类配置进去，新建一个config包，
     * 在config包里新建MyWebMvcConfigurer并实现WebMvcConfigurer，
     * 打上@Configuration注解。注意，要把自定义的参数解析器放在首位，这很重要
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 把自己写的参数解析器放在首位，提高命中率
        if (resolvers.isEmpty()) {
            resolvers.add(0, new RequestParamMethodPropertiesBodyArgumentConvertObjectResolver());
        } else {
            resolvers.set(0, new RequestParamMethodPropertiesBodyArgumentConvertObjectResolver());
        }
    }
}
