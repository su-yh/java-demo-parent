package com.suyh2203.controller;

import com.suyh2203.vo.PersonPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {
    @RequestMapping("/test")
    public String demoTestValidated(@RequestBody @Validated PersonPlus personPlus) {
        return "OK";
    }
}
