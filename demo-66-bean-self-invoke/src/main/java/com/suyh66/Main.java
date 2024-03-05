package com.suyh66;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author suyh
 * @since 2024-03-05
 */
@Configuration
@ComponentScan
// TODO: suyh - 为什么没效果
//@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        TestBean testBean = ctx.getBean(TestBean.class);
        testBean.hello();
    }

}
