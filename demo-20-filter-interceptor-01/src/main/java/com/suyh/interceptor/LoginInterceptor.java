package com.suyh.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    // 这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        // 获取token
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("Authorization: {}", authorization);

        String token = request.getHeader("token");

        //如果session中没有user，表示没登陆
        if (token == null) {
            log.info("token is null");
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆

            // 权限不足
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            // 指定返回数据的类型为json格式，utf-8字符集
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");

            // 在这里直接生成响应json 数据返回给前端
            String resJson = "{\n" +
                    "    \"timestamp\": \"2020-07-03T18:14:39.008+0000\",\n" +
                    "    \"status\": 404,\n" +
                    "    \"error\": \"Not Found\",\n" +
                    "    \"message\": \"No message available\",\n" +
                    "    \"path\": \"/suyh/filter/entity/dsafjkl\"\n" +
                    "}";
            response.getWriter().print(resJson);

            // 未通过校验，直接返回给前端结果
            return false;
        }
        log.info("token is not nul");

        // 通过校验，往下继续处理
        return true;
    }

    // 方法调用之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, @Nullable Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}

