package com.suyh.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author suyh
 * @since 2024-03-08
 * @deprecated 使用认证成功/失败的后置处理器之后就不需要实现这种异常拦截了。所有的异常拦截都让其内部跳转到一个url 里面再进行抛出业务异常即可。
 */
@Slf4j
@Deprecated
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("[commence][访问 URL({}) 时，用户权限不够]", request.getRequestURI());
    }
}
