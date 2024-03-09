package com.suyh.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败的后置处理
 *
 * @author suyh
 * @since 2024-03-08
 * @deprecated 使用认证成功/失败的后置处理器之后就不需要实现这种异常拦截了。所有的异常拦截都让其内部跳转到一个url 里面再进行抛出业务异常即可。
 */
@Slf4j
@Deprecated
public class AuthenticationEntryPointImpl
        implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.debug("[commence][访问 URL({}) 时，没有登录]", request.getRequestURI());
        // response.sendError(HttpStatus.UNAUTHORIZED.value());
        throw new RuntimeException("未认证");
    }
}
