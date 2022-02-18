package com.suyh2301.around.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 使用环境增来处理切面，可以在一个方法里面直接处理前中后。
 * 而不用去理解和怀疑其他的那几个注解的调用时机
 */
@Aspect
@Component
@Slf4j
public class AroundAspect {
    // `@Around`：环绕增强，相当于MethodInterceptor
    @Around("execution(* com.suyh2301.around.service.AroundService.*(..))")
    public Object execMethod(ProceedingJoinPoint point) throws Throwable {
        log.info("@Around：执行目标方法之前...");
        // 访问目标方法的参数：
        Object[] args = point.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
            args[0] = "改变后的参数1";
        }
        // 用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        log.info("@Around：执行目标方法之后...");
        log.info("@Around：被织入的目标对象为：" + point.getTarget());
        return "原返回值：" + returnValue + "，这是返回结果的后缀";
    }

    @Around("@annotation(com.suyh2301.around.annotation.MethodDuration)")
    public Object methodTime(ProceedingJoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        log.info("@Around.methodTime: before, 类【{}】的方法【{}】", className, methodName);
        // 这里我们没的填写参数，但是不管实际方法有什么参数都会正常传入
        long beforeMillis = System.currentTimeMillis();
        Object returnValue = point.proceed();
        long afterMillis = System.currentTimeMillis();
        log.info("@Around.methodTime: 类【{}】的方法【{}】，执行总耗时为：【{}ms】",
                className, methodName, afterMillis - beforeMillis);
        return returnValue;
    }
}
