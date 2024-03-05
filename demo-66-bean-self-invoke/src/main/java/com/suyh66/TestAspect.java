package com.suyh66;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author suyh
 * @since 2024-03-05
 */
@Aspect
@Component
public class TestAspect {

    // 定义切点（切入位置）
    @Pointcut("execution(* com.suyh66.TestBean.*(..))")
    private void pointcut(){}


    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        String name = method.getName();
        System.out.println("我是前置通知, method name: " + name);
    }

}
