package com.suyh66;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author suyh
 * @since 2024-03-05
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy(exposeProxy = false)
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        TestBean testBean = ctx.getBean(TestBean.class);
        testBean.hello();
        System.out.println("#####");
        testBean.hi();
    }

}
