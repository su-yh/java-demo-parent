package com.suyh.controller.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "其他控制器-value", tags = {"其他控制器-tags"})
@RestController
public class OtherGroupController {
    @ApiOperation(value = "其他控制器一个请求",
            notes = "其他控制器详细请求描述")
    @RequestMapping("/other/path")
    public String getOtherInfo() {
        return "OK";
    }
}
