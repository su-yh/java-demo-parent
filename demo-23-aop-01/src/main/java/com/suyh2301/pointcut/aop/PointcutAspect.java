package com.suyh2301.pointcut.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * `@Around`：环绕增强，相当于MethodInterceptor
 *
 * `@Before`：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
 *
 *
 *
 */
@Component
@Slf4j
@Aspect
public class PointcutAspect {

    /**
     * 对切面各注解调用时机的形象表现
     */
    public void method() {
        try {
            // `@Before` 调用时间
            TimeUnit.SECONDS.sleep(100000000);  // 当前切面关注的业务方法
            // `@AfterReturn` 调用时间
        } catch(Exception e) {
            // `@AfterThrowing` 调用时间
        } finally {
            // `@After` 调用时间
        }
    }

    /**
     * `@Pointcut`：Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，
     *      一是表达式，二是方法签名。方法签名必须是 public void型。
     *      可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，
     *      因此我们可以通过方法签名的方式为此表达式命名。
     *      因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
     */
    @Pointcut("within(com.suyh2301.pointcut.service.PointcutService)")
    public void pointCut() {
        // do nothing
    }

    // `@Before`：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
    // 注意：这里面'()' 是不能少的，不然会报错
    @Before("pointCut()")
    public void beforeInfo() {
        log.info("NormalAspect.beforeInfo()");
    }

    // `@After`: final增强，不管是抛出异常或者正常退出都会执行
    @After("pointCut()")
    public void afterInfo() {
        log.info("NormalAspect.afterInfo()");
    }

    // `@AfterReturning`：后置增强，相当于AfterReturningAdvice，方法正常退出时执行
    @AfterReturning(pointcut = "pointCut()")
    public void afterReturn() {
        log.info("NormalAspect.afterReturn");
    }

    // `@AfterThrowing`：异常抛出增强，相当于ThrowsAdvice
    @AfterThrowing(pointcut = "pointCut()")
    public void afterThrowing() {
        log.info("NormalAspect.afterThrowing");
    }
}
