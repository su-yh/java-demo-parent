package com.suyh2103.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@Api(value = "示例demo，异常控制器", tags = {"demo-exception-controller"})
@RestController
@RequestMapping("/suyh2103/exception")
public class DemoExceptionController {

    @ApiOperation(value = "标签显示：RuntimeException", notes = "接口描述：抛出一个运行时异常")
    // 指定所有的参数列表
    @ApiImplicitParams({})
    @RequestMapping(value = "/exception01", method = RequestMethod.GET)
    public String exception01() {
        throw new RuntimeException("exception01");
    }

    @ApiOperation(value = "标签显示：ServletException", notes = "接口描述：抛出一个ServletException 异常")
    @RequestMapping(value = "/servletException", method = RequestMethod.GET)
    public String servletException() throws ServletException {
        throw new ServletException();
    }
}
