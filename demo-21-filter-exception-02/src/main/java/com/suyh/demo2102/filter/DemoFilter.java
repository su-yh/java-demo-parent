package com.suyh.demo2102.filter;

import com.suyh.demo2102.exception.SuyhException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
 *      触发时间不同，Filter 先于 interceptor 触发
 */
@Component
@Slf4j
public class DemoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("uri: {}", request.getRequestURI());
        log.info("HttpHeaders.AUTHORIZATION: {}", token);

        if (token == null || token.length() == 0) {
            throw new SuyhException("suyh filter exception message");
        }

        // 继续下一个过滤器
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
