package com.suyh.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suyh
 * @since 2023-11-02
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello security";
    }

    // 登录成功之后跳转的接口
    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('admins')")
    public String index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "hello index";
    }
}
