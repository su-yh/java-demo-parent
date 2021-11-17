package com.suyh.controller;

import com.suyh.exception.SuyhException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义异常捕获
 */
@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {
    @ExceptionHandler(SuyhException.class)
    public String customSuyhException(SuyhException e, HttpServletResponse response) {
        try {
            /*
             * 返回信息如下，但是这个时间要怎么弄呢？
             * {
             *   "timestamp": "2021-11-17T13:29:23.322+0000",
             *   "status": 11011,
             *   "error": "Http Status 11011",
             *   "message": "other suyh exception message",
             *   "path": "/req/suyh/exception"
             * }
             */
            /*
             * 使用自定义的ObjectMapper 之后，这个时间也可以显示成我们想要的时间了。
             * {
             *   "timestamp": "2021-11-17 21:42:28.506",
             *   "status": 11011,
             *   "error": "Http Status 11011",
             *   "message": "other suyh exception message",
             *   "path": "/req/suyh/exception"
             * }
             */
            // 通过这里返回状态码和错误消息给前端
            response.sendError(11011, "other suyh exception message");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        log.error("get SuyhException， message: {}", e.getMessage());
        return "suyh exception";
    }
}
