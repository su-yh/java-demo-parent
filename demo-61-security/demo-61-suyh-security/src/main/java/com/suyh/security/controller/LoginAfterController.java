package com.suyh.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证后置处理器
 *
 * @author suyh
 * @since 2024-03-08
 */
@RestController
@RequestMapping("/login/after")
public class LoginAfterController {

    // 该接口由spring security 自动调用，在用户通过了帐号密码校验之后，会来访问该接口。
    // 不过需要自行配置，在WebSecurityConfigurerAdapter 中。
    // 这种方式不好，因为重定向后被改成了GET 方法，同时参数全都丢失了，虽然给补充了一个Authentication 参数。
    @RequestMapping(value = "/successful", method = {RequestMethod.GET})
    @Deprecated
    public Object loginSuccessful(Authentication authentication) {
        return authentication.getPrincipal();
    }

    // TODO: suyh - 这里的问题主要是没有Authentication，有空的时候看下 SavedRequestAwareAuthenticationSuccessHandler  是怎么把这些参数补充上的
    // 不过这里保留了登录时的原始参数。
    @RequestMapping(value = "/successful", method = RequestMethod.POST)
    public Object loginSuccessfulP(String username, String password, Integer code) {
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("code: " + code);
        return "SUCCESSFUL";
    }

    // 如果登录失败，一般是用户名或者密码错误。
    // 在这里实现，可以返回给前端自定义的一些数据信息，或者提示。
    @RequestMapping(value = "/failure", method = RequestMethod.POST)
    public Object loginFailure() {
        return "FAILED";
    }
}
