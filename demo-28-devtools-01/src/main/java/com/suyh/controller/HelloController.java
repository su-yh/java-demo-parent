package com.suyh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Value("${server.port}")
    private int port;

    // 这里修改之后总是报404 ，不知道是不是controller 不能改
    // 还有就是非debug 模式是没有问题的。
    @RequestMapping("/name")
    public String name() {
        return "current modify  -  port: " + port;
    }
}
