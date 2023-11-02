package com.suyh6001.controller;

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

//    public static void main(String[] args) {
//        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
//        // 对密码进行加密
//        String pwd = b.encode("suyunhong");
//        // 打印加密之后的数据
//        System.out.println(pwd);
//
//        // 判断原字符串加密后和加密之前是否匹配
//        boolean result = b.matches("suyunhong", pwd);
//        System.out.println(result);
//    }
}
