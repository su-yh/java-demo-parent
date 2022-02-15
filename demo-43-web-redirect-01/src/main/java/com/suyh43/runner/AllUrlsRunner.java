package com.suyh43.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;


@Component
public class AllUrlsRunner implements ApplicationRunner {
    @Resource
    private WebApplicationContext webContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RequestMappingHandlerMapping mapping = webContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        handlerMethods.forEach((info, method) -> {
            Set<String> urls = info.getPatternsCondition().getPatterns();
            System.out.println("urls: " + urls);
        });
    }
}
