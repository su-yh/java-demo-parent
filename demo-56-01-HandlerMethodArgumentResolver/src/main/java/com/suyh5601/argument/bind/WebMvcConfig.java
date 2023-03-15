package com.suyh5601.argument.bind;

import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrUserArgumentResolver());
        argumentResolvers.add(new UserArgumentResolverAndValidation());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginUserInterceptor loginInterceptor = new LoginUserInterceptor();
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register");
    }
}
