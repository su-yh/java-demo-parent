package com.suyh1203.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是我在官网上抄的
 *
 * @author suyh
 * @since 2023-09-21
 */
@RestController
@RequestMapping("/body")
@Tag(name = "body参数")
public class BodyController {

    @Operation(summary = "普通body请求")    // 这个是在swagger 文档中显示的文本
    @RequestMapping(value = "/body", method = RequestMethod.GET)
    public String body() {
        return "OK";
    }

    @Operation(summary = "普通body请求+Param+Header+Path")
    @Parameters({
            @Parameter(name = "id", description = "文件id", in = ParameterIn.PATH),
            @Parameter(name = "token", description = "请求token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "name", description = "文件名称", required = true, in = ParameterIn.QUERY)
    })
    @RequestMapping(value = "/bodyParamHeaderPath/{id}", method = RequestMethod.GET)
    public String bodyParamHeaderPath(
            @PathVariable("id") String id, @RequestHeader("token") String token, @RequestParam("name") String name) {
        return "finished";
    }
}

