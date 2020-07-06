package com.suyh.controller;

import com.suyh.exception.SuyhException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局性的异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 对 Exception 异常的处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String customException(Exception e) {
        log.error("get Exception");
        return "error result";
    }

    /**
     * 对运行时异常的处理
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String customRuntimeException(RuntimeException e) {
        log.error("get RuntimeException");
        return "error runtime exception";
    }

    @ExceptionHandler(SuyhException.class)
    public String customSuyhException(SuyhException e) {
        log.error("get SuyhException");
        return "suyh exception";
    }

}
