package com.suyh.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证后置处理器
 *
 * @author suyh
 * @since 2024-03-08
 * @deprecated spring security 如果使用转发的话，那么参数就会有丢失。所以最好的处理就是使用自定义的成功/失败后置处理器
 */
@RestController
@RequestMapping("/login/post")
@Deprecated
public class LoginPostController {

    // 该接口由spring security 自动调用，在用户通过了帐号密码校验之后，会来访问该接口。不过需要自行配置，在WebSecurityConfigurerAdapter 中。
    @RequestMapping(value = "/successful", method = RequestMethod.GET)
    public Object loginSuccessful(Authentication authentication, @RequestParam("code") Integer code) {
        return authentication.getPrincipal();
    }

    // 如果登录失败，一般是用户名或者密码错误。
    // 在这里实现，可以返回给前端自定义的一些数据信息，或者提示。
    @RequestMapping(value = "/failure", method = RequestMethod.POST)
    public Object loginFailure() {
        return "FAILED";
    }
}
