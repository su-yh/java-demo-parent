package com.suyh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class ServletRequestService {

    /**
     * 通过此现象可以得出，通过 RequestContextHolder.getRequestAttributes() 所得到的上下文数据是不能跨线程的。
     */
    @Async
    public void temp() {
        log.info("ServletRequestService#temp(), current thread: {}, tokenValue: {}", Thread.currentThread().getName(), "");

        // 获取请求上下文，从中提取出header 中的token 数据
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String tokenValue = request.getHeader("token");
        log.info("ServletRequestService#temp(), current thread: {}, tokenValue: {}", Thread.currentThread().getName(), tokenValue);
    }
}
