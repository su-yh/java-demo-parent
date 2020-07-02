package com.suyh.service.controller;

import com.suyh.service.ExampleDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class ExampleDemoAutowiredController {
    @Autowired
    @Qualifier("exampleDemoServiceImpl01")
    private ExampleDemoService demoAutowiredService01;

    @RequestMapping("/autowired01/name")
    public String getNameAutowired01() {
        return demoAutowiredService01.getClassName() + " | " + new Date();
    }

    @Autowired
    @Qualifier("exampleDemoServiceImpl02")
    private ExampleDemoService demoAutowiredService02;

    @RequestMapping("/autowired02/name")
    public String getNameAutowired02() {
        return demoAutowiredService02.getClassName() + " | " + new Date();
    }

}
