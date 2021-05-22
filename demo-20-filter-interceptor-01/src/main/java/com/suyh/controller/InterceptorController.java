package com.suyh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
@Slf4j
public class InterceptorController {

    @RequestMapping("/suyh/name")
    public String name() {
        return "suyh";
    }

}
