package com.suyh4002.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suyh")
public class SuyhController {

    @RequestMapping("/name")
    public String name() {
        return "suyh";
    }
}
