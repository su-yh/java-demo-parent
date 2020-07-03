package com.suyh.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现一个自自定过滤器
 * 方法一:
 * 1. 需要实现 Filter 接口
 * 2. 需要添加 @Component 注解
 *
 * 另外，Filter 与 interceptor 的区别
 *      触发时间不同，Filter 是在 interceptor 以及 RequestServlet 之后触发的
 */
@Component
@Slf4j
public class FirstFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("uri: {}", request.getRequestURI());
        log.info("HttpHeaders.AUTHORIZATION: {}", request.getHeader(HttpHeaders.AUTHORIZATION));

        // 继续下一个过滤器
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
