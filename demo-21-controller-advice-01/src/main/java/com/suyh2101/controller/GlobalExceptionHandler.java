package com.suyh2101.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 全局性的异常处理
 * RequestBodyAdvice: 在进入到controller 中之前做一些处理
 * ResponseBodyAdvice: 在离开controller 之后做一些处理
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
    public String customException(Exception e, HttpServletResponse response) {
        log.error("get Exception, message: {}", e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error result";
    }

    /**
     * 对运行时异常的处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String customRuntimeException(RuntimeException e, HttpServletResponse response) {
        log.error("get RuntimeException, message: {}", e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
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
    public String handlerException(MethodArgumentNotValidException exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
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
    public String handlerException(WebExchangeBindException exception, HttpServletResponse response) {
        log.error("get RuntimeException, message: {}", exception.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
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
    public String handlerException(BindException exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
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

    /**
     * 请求头缺失异常
     * message: Required request header '请求头' for method parameter type 参数类型(String) is not present
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public String handlerException(MissingRequestHeaderException exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return exception.getMessage();
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public String handlerException(MissingServletRequestPartException exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return exception.getMessage();
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
    String handleControllerException(SQLException exception, HttpServletResponse response) {
        log.error("CelonBpmCoreExceptionHandler, SQLException", exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
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
    String handleControllerException(NullPointerException exception, HttpServletResponse response) {
        log.error("CelonBpmCoreExceptionHandler, NullPointerException", exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
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
     * 缺少必须参数异常处理
     * required = true
     *
     * @param exception exception
     * @return result
     */
    @ExceptionHandler(value = {ServletRequestBindingException.class})
    String handleControllerException(ServletRequestBindingException exception, HttpServletResponse response) {
        log.error("failed, ServletRequestBindingException", exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return makeResult(-1, exception.getMessage());
    }
    
    /**
     * 非法参数异常
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    String handleControllerException(IllegalArgumentException exception, HttpServletResponse response) {
        log.error("CelonBpmCoreExceptionHandler, IllegalArgumentException", exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
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
