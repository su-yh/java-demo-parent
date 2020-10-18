package com.suyh.controller;

import com.suyh.exception.SuyhException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常捕获
 */
@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {
    @ExceptionHandler(SuyhException.class)
    public String customSuyhException(SuyhException e) {
        log.error("get SuyhException， message: {}", e.getMessage());
        return "suyh exception";
    }
}
