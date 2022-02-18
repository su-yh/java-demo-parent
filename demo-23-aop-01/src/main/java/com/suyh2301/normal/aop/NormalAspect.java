package com.suyh2301.normal.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class NormalAspect {

    // 对指定的方法进行前置拦截
    @Before("execution(* com.suyh.normal.service.*.*(..))")
    public void permissionCheck(JoinPoint point) {
        log.info("@Before：模拟权限检查...");
        log.info("@Before：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        log.info("@Before：参数为：" + Arrays.toString(point.getArgs()));
        log.info("@Before：被织入的目标对象为：" + point.getTarget());
    }

    /**
     * @param point       被切入的点
     * @param returnValue 在注解上指定的可以接收的形参(returning="returnValue")，该值为切入方法的返回值
     */
    @AfterReturning(pointcut = "execution(* com.suyh.normal.service.*.*(..))",
            returning = "returnValue")
    public void log(JoinPoint point, Object returnValue) {
        log.info("@AfterReturning：模拟日志记录功能...");
        log.info("@AfterReturning：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        log.info("@AfterReturning：参数为：" +
                Arrays.toString(point.getArgs()));
        log.info("@AfterReturning：返回值为：" + returnValue);
        log.info("@AfterReturning：被织入的目标对象为：" + point.getTarget());
    }

    @After("execution(* com.suyh.normal.service.*.*(..))")
    public void releaseResource(JoinPoint point) {
        log.info("@After：模拟释放资源...");
        log.info("@After：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        log.info("@After：参数为：" + Arrays.toString(point.getArgs()));
        log.info("@After：被织入的目标对象为：" + point.getTarget());
    }
}
