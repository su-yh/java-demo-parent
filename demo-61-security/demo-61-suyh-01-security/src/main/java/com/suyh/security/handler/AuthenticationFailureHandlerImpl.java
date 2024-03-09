package com.suyh.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败的后置处理器
 *
 * @author suyh
 * @since 2024-03-08
 */
@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.trace("认证失败了。");

        // 使用 RequestDispatcher 对象进行请求转发可以实现服务器端的内部跳转，而不会改变客户端的 URL。
        // 转发后，服务器端会处理新的请求，并将结果直接返回给客户端，客户端不会感知到转发的过程，因此 URL 也不会改变。
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login/after/failure");
        requestDispatcher.forward(request, response);
    }
}
