package com.suyh2201.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局性的异常处理
 * 默认禁止
 */
@RestControllerAdvice
@ConditionalOnProperty(name = "suyh.server.controller.exception.global", havingValue = "true")
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 对 Exception 异常的处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String customException(Exception e) {
        log.error("get Exception, message: {}", e.getMessage(), e);
        return e.getMessage();
    }

    /**
     * 对运行时异常的处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String customRuntimeException(RuntimeException e) {
        log.error("get RuntimeException, message: {}", e.getMessage(), e);
        return "error runtime exception";
    }
}
