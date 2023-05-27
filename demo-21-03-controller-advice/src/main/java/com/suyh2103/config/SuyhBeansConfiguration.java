package com.suyh2103.config;

import com.suyh2103.filter.ExceptionResultFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;

@Configuration
public class SuyhBeansConfiguration {
    @Bean
    public FilterRegistrationBean<Filter> delegatingFilterProxy(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        ExceptionResultFilter filter = new ExceptionResultFilter();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1000);
        return filterRegistrationBean;
    }
}
