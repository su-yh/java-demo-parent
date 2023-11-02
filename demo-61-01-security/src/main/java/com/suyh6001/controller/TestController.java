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
}
