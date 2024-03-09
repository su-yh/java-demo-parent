package com.suyh.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功/失败后的处理
 *
 * @author suyh
 * @since 2024-03-08
 */
@Slf4j
public class AuthenticationAfterForwardHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    private final String successForwardUrl;
    private final String failureForwardUrl;

    public AuthenticationAfterForwardHandler(
            @NonNull String successForwardUrl, @NonNull String failureForwardUrl) {
        this.successForwardUrl = successForwardUrl;
        this.failureForwardUrl = failureForwardUrl;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        log.trace("认证通过了。");

        // 使用 RequestDispatcher 对象进行请求转发可以实现服务器端的内部跳转，而不会改变客户端的 URL。
        // 转发后，服务器端会处理新的请求，并将结果直接返回给客户端，客户端不会感知到转发的过程，因此 URL 也不会改变。
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(successForwardUrl);
        requestDispatcher.forward(request, response);
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        log.trace("认证失败了。");

        // 使用 RequestDispatcher 对象进行请求转发可以实现服务器端的内部跳转，而不会改变客户端的 URL。
        // 转发后，服务器端会处理新的请求，并将结果直接返回给客户端，客户端不会感知到转发的过程，因此 URL 也不会改变。
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(failureForwardUrl);
        request.setAttribute("msg", exception.getMessage());
        requestDispatcher.forward(request, response);
    }
}
