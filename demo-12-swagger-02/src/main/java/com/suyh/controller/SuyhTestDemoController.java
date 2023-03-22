package com.suyh.controller;

import com.suyh.config.Swagger3ConfigurationPlus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "其他控制器-value", tags = {"其他控制器-tags"})
@RestController
public class SuyhTestDemoController {
    @ApiOperation(value = "测试demo",
            // authorizations 表示这个接口需要添加认证或者鉴权的相关信息。
            authorizations = {@Authorization(value = Swagger3ConfigurationPlus.AUTH_KEY)})
    @RequestMapping("/demo/temp/test")
    public String getOtherInfo() {
        return "OK";
    }
}
