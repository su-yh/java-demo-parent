package com.suyh.demo2102.handler.controller;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 对filter 或者 interceptor 的异常的拦截处理
 * 刚测试了一下，当该controller 存在，且@RestControllerAdvice 实现的类也存在，则filter 里面的异常将会走此异常处理类
 * 而mvc 的异常，将会按正常的@RestControllerAdvice 的实现类处理。
 * 如果没有实现@RestControllerAdvice 的实现类，则异常全部都会走这里。
 *
 * 另外参考：https://blog.csdn.net/L_Sail/article/details/70198886
 */
@Controller
public class FilterExceptionController extends BasicErrorController {

    public FilterExceptionController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        /*
         * body 中的几个基础参数
         *
         * {
         * "timestamp": "2021-04-11T15:49:59.854+0000",
         * "status": 200,
         * "error": "Internal Server Error",
         * "message": "suyh exception message",
         * "path": "/filter/req/runtime/exception"
         * }
         * Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
         */
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.OK.value());
        HttpHeaders headers  = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(body, headers, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 在request 的请求头中有指定一个 "Accept: text/html" 时，会走该方法。
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        response.setStatus(status.value());
//        final Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        final Map<String, Object> model = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.TEXT_HTML));
        final ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
    }
}
