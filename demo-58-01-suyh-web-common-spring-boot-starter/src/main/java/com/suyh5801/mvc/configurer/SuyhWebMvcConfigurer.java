package com.suyh5801.mvc.configurer;

import com.suyh5801.mvc.error.SuyhHandlerExceptionResolver;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;

@Component
public class SuyhWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void extendHandlerExceptionResolvers(@NonNull List<HandlerExceptionResolver> resolvers) {
        // 将spring mvc 创建的DefaultHandlerExceptionResolver 删除掉，使用自定义的DefaultHandlerExceptionResolver 派生类替代
        int i = 0;
        for (; i < resolvers.size(); i++) {
            HandlerExceptionResolver resolver = resolvers.get(i);
            if (DefaultHandlerExceptionResolver.class.isAssignableFrom(resolver.getClass())) {
                break;
            }
        }

        if (i < resolvers.size()) {
            resolvers.remove(i);
        }

        resolvers.add(new SuyhHandlerExceptionResolver());
    }
}
