package com.suyh.around.service;

import com.suyh.around.annotation.MethodDuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
// @EnableAspectJAutoProxy 配置可以使用AopContext.currentProxy();
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SayHelloAroundService {
    @MethodDuration
    public void sayHelloVoid() {
        log.info("SayHelloAroundService.sayHelloVoid");
        try {
            TimeUnit.MILLISECONDS.sleep(100 + new Random().nextInt(100));
        } catch (InterruptedException ignored) {
        }
    }

    @MethodDuration
    public void sayHelloString(String msg) {
        // 要在同一个bean对象的方法中调用另一个方法，本来是不会被AOP 拦截到的，
        // 但是我们可以通过线程代码的方式来得到相关的代理，使得可以AOP 拦截。
        SayHelloAroundService self = (SayHelloAroundService) AopContext.currentProxy();
        self.sayHelloVoid();
        log.info("SayHelloAroundService.sayHelloString({})", msg);

        try {
            TimeUnit.MILLISECONDS.sleep(300 + new Random().nextInt(100));
        } catch (InterruptedException ignored) {
        }
    }
}
