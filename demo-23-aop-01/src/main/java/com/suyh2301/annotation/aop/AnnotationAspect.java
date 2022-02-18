package com.suyh2301.annotation.aop;

import com.suyh2301.annotation.service.SmsAndMailSender;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AnnotationAspect {
    /**
     * 在所有标记了@SmsAndMailSender 的方法中切入
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "@annotation(com.suyh2301.annotation.service.SmsAndMailSender)",
            returning = "result")//有注解标记的方法，执行该后置返回
    public void afterReturning(JoinPoint joinPoint, Object result/* 注解标注的方法返回值 */) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        boolean active = method.getAnnotation(SmsAndMailSender.class).isActive();
        if (!active) {
            return;
        }
        String smsContent = method.getAnnotation(SmsAndMailSender.class).smsContent();
        String mailContent = method.getAnnotation(SmsAndMailSender.class).mailContent();
        String subject = method.getAnnotation(SmsAndMailSender.class).subject();

        log.info("[AnnotationAspect.afterReturning] smsContent = {}, mailContent = {}, subject = {}",
                smsContent, mailContent, subject);
    }


    /**
     * 在抛出异常时使用
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "@annotation(com.suyh2301.annotation.service.SmsAndMailSender)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex /* 注解标注的方法抛出的异常 */) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        String subject = method.getAnnotation(SmsAndMailSender.class).subject();

        log.info("[AnnotationAspect.afterThrowing] subject = {}", subject);
    }
}
