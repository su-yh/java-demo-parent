package com.suyh.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证后置处理器
 *
 * @author suyh
 * @since 2024-03-08
 */
@RestController
@RequestMapping("/login/after")
public class LoginPostController {

    // 该接口由spring security 自动调用，在用户通过了帐号密码校验之后，会来访问该接口。不过需要自行配置，在WebSecurityConfigurerAdapter 中。
    @RequestMapping(value = "/successful", method = {RequestMethod.GET})
    public Object loginSuccessful(Authentication authentication) {
        return authentication.getPrincipal();
    }

    @RequestMapping(value = "/successful", method = RequestMethod.POST)
    public Object loginSuccessfulP(HttpServletRequest request) {
        return "SUCCESSFUL";
    }

    // 如果登录失败，一般是用户名或者密码错误。
    // 在这里实现，可以返回给前端自定义的一些数据信息，或者提示。
    @RequestMapping(value = "/failure", method = RequestMethod.POST)
    public Object loginFailure() {
        return "FAILED";
    }
}
