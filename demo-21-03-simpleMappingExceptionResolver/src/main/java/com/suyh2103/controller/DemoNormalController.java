package com.suyh2103.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "示例demo，正常请求控制器", tags = {"demo-normal-controller"})
@RestController
@RequestMapping("/suyh2103/normal")
public class DemoNormalController {

    @ApiOperation(value = "这是一个正常的接口", notes = "正常常接口")
    @RequestMapping(value = "/method-01", method = RequestMethod.GET)
    public String normal01() {
        return "nothing";
    }
}
