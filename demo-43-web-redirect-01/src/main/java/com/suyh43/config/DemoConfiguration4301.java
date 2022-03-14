package com.suyh43.config;

import com.suyh43.filter.RoutingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration(proxyBeanMethods = false)
public class DemoConfiguration4301 {
    @Bean
    public FilterRegistrationBean<Filter> routingFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RoutingFilter());
        // 是否支持异步处理
        registration.setAsyncSupported(true);
        registration.addUrlPatterns("/*");
        registration.setName("routingFilter");
        registration.setOrder(1);

        return registration;
    }
}
