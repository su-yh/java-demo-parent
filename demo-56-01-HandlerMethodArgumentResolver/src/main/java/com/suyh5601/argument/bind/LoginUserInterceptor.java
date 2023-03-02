package com.suyh5601.argument.bind;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class LoginUserInterceptor implements HandlerInterceptor {

    public static void doResponse(HttpServletResponse response, int status) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status);
        response.getWriter().println("failed.");
    }

    // 这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String userToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(userToken)) {
            doResponse(response, HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        CurrUserVo userVo = new CurrUserVo();
        userVo.setId(1L);
        userVo.setName("suyh");

        request.setAttribute("currLoginUser", userVo);
        return true;
    }
}