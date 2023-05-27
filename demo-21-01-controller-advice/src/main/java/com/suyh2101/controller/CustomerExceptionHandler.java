package com.suyh2101.controller;

import com.suyh2101.exception.SuyhException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常捕获
 */
@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {
    @ExceptionHandler(SuyhException.class)
    public String customSuyhException(SuyhException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error("get SuyhException， message: {}", e.getMessage());
        return "suyh exception";
    }
}
