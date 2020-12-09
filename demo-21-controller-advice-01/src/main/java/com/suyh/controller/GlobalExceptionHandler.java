package com.suyh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;

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
        log.error("get Exception, message: {}", e.getMessage());
        return "error result";
    }

    /**
     * 对运行时异常的处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String customRuntimeException(RuntimeException e) {
        log.error("get RuntimeException, message: {}", e.getMessage());
        return "error runtime exception";
    }

    /**
     * 参数校验失败异常，这时原异常一般是Controller 的请求实体对象进行参数绑定时，添加了@Valid 注解
     * 然后在实体对象上添加了@Size(max = 10) 这种。如果传入的值为11，则会报这个异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handlerException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return resultMessage(bindingResult);
    }

    /**
     * 在开课吧中雷老师讲的对参数异常校验的处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public String handlerException(WebExchangeBindException exception) {
        log.error("get RuntimeException, message: {}", exception.getMessage());
        return exception.getFieldErrors().stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }

    /**
     * 参数绑定异常，这也是Controller 的请求实体对象进行参数校验时出现的异常。
     * 如果在实体对象上添加了@Size(max = 10) 这种。如果传入的值不是数字类型，则会报这个绑定异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    public String handlerException(BindException exception) {
        return resultMessage(exception.getBindingResult());
    }

    private String resultMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError error : errors) {
            String defaultMessage = error.getDefaultMessage();
            sb.append(defaultMessage).append(" ");
        }
        return sb.toString();
    }
}
