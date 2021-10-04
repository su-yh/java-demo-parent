package com.suyh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

//    /**
//     * JDBC的连接池异常，不能获取JDBC的连接，连接池上限
//     *
//     * @param exception the exception
//     * @return the response entity
//     */
//    @ExceptionHandler(value = {org.springframework.jdbc.CannotGetJdbcConnectionException.class})
//    String handleControllerException(CannotGetJdbcConnectionException exception) {
//        log.error("GlobalExceptionHandler, CannotGetJdbcConnectionException", exception);
//        String message = "JdbcConnection. Sorry,The server is busy，please Wait for 10 seconds and try again";
//        return makeResult(1, message);
//    }

    /**
     * Handle controller exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {SQLException.class})
    String handleControllerException(SQLException exception) {
        log.error("CelonBpmCoreExceptionHandler, SQLException", exception);
        String message = "internal error【SQL】. Please contact the jalor bpm administrator.";
        return makeResult(1, message);
    }

    /**
     * Handle controller exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {NullPointerException.class})
    String handleControllerException(NullPointerException exception) {
        log.error("CelonBpmCoreExceptionHandler, NullPointerException", exception);
        String message = exception.getMessage();
        String result = null;
        if (StringUtils.isEmpty(message)) {
            result = makeResult(1, "NullPointerException");
        } else {
            result = makeResult(1, message);
        }
        return result;
    }

    /**
     * 参数校验异常处理
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    String handleControllerException(MethodArgumentNotValidException exception) {
        log.error("CelonBpmCoreExceptionHandler, MethodArgumentNotValidException", exception);
        return handleBindException(exception.getBindingResult());
    }

    /**
     * 缺少必须参数异常处理
     * required = true
     *
     * @param exception exception
     * @return result
     */
    @ExceptionHandler(value = {ServletRequestBindingException.class})
    String handleControllerException(ServletRequestBindingException exception) {
        log.error("failed, ServletRequestBindingException", exception);
        return makeResult(-1, exception.getMessage());
    }
    
    /**
     * 参数绑定异常处理
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {BindException.class})
    String handleControllerException(BindException exception) {
        log.error("CelonBpmCoreExceptionHandler, BindException", exception);
        return handleBindException(exception.getBindingResult());
    }

    /**
     * 非法参数异常
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    String handleControllerException(IllegalArgumentException exception) {
        log.error("CelonBpmCoreExceptionHandler, IllegalArgumentException", exception);
        return makeResult(1, exception.getMessage());
    }

    /**
     * 参数返回值的异常统一返回结果
     *
     * @param bindingResult 绑定结果
     * @return 返回值
     */
    public static String handleBindException(BindingResult bindingResult) {
        List<ObjectError> errorList = bindingResult.getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError error : errorList) {
            if (error instanceof FieldError) {
                FieldError curError = (FieldError) error;
                errorMessage.append(curError.getField()).append(" ")
                        .append(curError.getDefaultMessage()).append(" ");
            } else {
                String defaultMessage = error.getDefaultMessage();
                errorMessage.append(defaultMessage).append(" ");
            }
        }
        return makeResult(1, errorMessage.toString());
    }

    /**
     * 通过统一的错误码构造返回值
     *
     * @param errorCode 错误码
     * @param message   错误提示
     * @return 返回结果
     */
    public static String makeResult(int errorCode, String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
        String formatMessage = String.format(Locale.ROOT, "error code - %03d: 【%s】 %s ",
                errorCode, sdf.format(new Date()), message);
        log.info("makeResult, formatMessage: {}", formatMessage);
        return formatMessage;
    }
}
