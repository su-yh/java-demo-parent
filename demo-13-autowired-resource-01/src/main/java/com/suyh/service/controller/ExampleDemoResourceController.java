package com.suyh.service.controller;

import com.suyh.service.ExampleDemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class ExampleDemoResourceController {

    @Resource(name = "exampleDemoServiceImpl01")
    private ExampleDemoService demoResourceService01;

    @RequestMapping("/resource01/name")
    public String getNameResource01() {
        return demoResourceService01.getClassName() + " | " + new Date();
    }

    @Resource(name = "exampleDemoServiceImpl02")
    private ExampleDemoService demoResourceService02;

    @RequestMapping("/resource02/name")
    public String getNameResource02() {
        return demoResourceService02.getClassName() + " | " + new Date();
    }
}
