package com.suyh.service.controller;

import com.suyh.service.ExampleDemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 使用@Resource 注入Bean 对象，通过name 属性来指定相同类型不同ID 的bean
 * 注解@Resource 默认是按ID进行注入的
 */
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
