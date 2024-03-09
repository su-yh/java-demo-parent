package com.suyh.security.controller;

import com.suyh.security.service.SuyhUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证后置处理器
 *
 * @author suyh
 * @since 2024-03-08
 */
@RestController
@RequestMapping("/login/after")
@RequiredArgsConstructor
public class LoginAfterController {
    private final ApplicationContext applicationContext;

    // 该接口由spring security 自动调用，在用户通过了帐号密码校验之后，会来访问该接口。
    // 不过需要自行配置，在WebSecurityConfigurerAdapter 中。
    // 这种方式不好，因为重定向后被改成了GET 方法，同时参数全都丢失了，虽然给补充了一个Authentication 参数。
    @RequestMapping(value = "/successful", method = {RequestMethod.GET})
    @Deprecated
    public Object loginSuccessful(Authentication authentication) {
        // 走到这里就表示用户名密码验证通过了，接下来就可以做后续处理了。
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = (User) userDetails;

        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());

        return SuyhUserDetailsService.createToken(map, user.getUsername());
    }

    // TODO: suyh - 这里的问题主要是没有Authentication，有空的时候看下 SavedRequestAwareAuthenticationSuccessHandler  是怎么把这些参数补充上的
    // 不过这里保留了登录时的原始参数。
    @RequestMapping(value = "/successful", method = RequestMethod.POST)
    public Object loginSuccessfulP(String username, String password, Integer code) {
        // 走到这里就表示用户名密码验证通过了，接下来就可以做后续处理了。
        // 这里就可以处理验证码什么的。
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("code: " + code);

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        return SuyhUserDetailsService.createToken(map, username);
    }



    // 如果登录失败，一般是用户名或者密码错误。
    // 在这里实现，可以返回给前端自定义的一些数据信息，或者提示。
    @RequestMapping(value = "/failure", method = RequestMethod.POST)
    public Object loginFailure() {
        // 因为我们就使用了spring security 的用户名密码校验的操作，如果调用到这里就一定是用户名或者密码错误。
        return "用户名或密码错误。";
    }
}
