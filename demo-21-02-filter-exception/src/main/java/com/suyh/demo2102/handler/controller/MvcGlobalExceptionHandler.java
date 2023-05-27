package com.suyh.demo2102.handler.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局性的异常处理
 * 默认禁止
 */
@RestControllerAdvice
@ConditionalOnProperty(name = "suyh.server.controller.exception.global", havingValue = "true", matchIfMissing = true)
@Slf4j
public class MvcGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String customException(Exception e) {
        log.error("get Exception, message: {}", e.getMessage());
        return "error result";
    }

    @ExceptionHandler(RuntimeException.class)
    public String customRuntimeException(RuntimeException e) {
        log.error("get RuntimeException, message: {}", e.getMessage());
        return "error runtime exception";
    }
}
