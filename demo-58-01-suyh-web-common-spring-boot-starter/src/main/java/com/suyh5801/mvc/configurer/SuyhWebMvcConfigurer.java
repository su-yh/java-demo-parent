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
        // 为什么要这样做：因为异常处理他是按顺序处理的，如果这里不删除掉，那么很多的异常处理都是按这个异常处理来做的。
        // 还有一个就是我们自定义的那个异常处理类SuyhHandlerExceptionResolver 它是派生彼默认异常处理类的，所以拥有它的一切功能，只是我们添加了一些扩展。
        // 所以直接删除，然后使用自定义实现的那个就可以了。
        for (int i = 0; i < resolvers.size(); i++) {
            HandlerExceptionResolver resolver = resolvers.get(i);
            if (DefaultHandlerExceptionResolver.class.isAssignableFrom(resolver.getClass())) {
                resolvers.remove(i);
                break;
            }
        }

        resolvers.add(new SuyhHandlerExceptionResolver());
    }
}
