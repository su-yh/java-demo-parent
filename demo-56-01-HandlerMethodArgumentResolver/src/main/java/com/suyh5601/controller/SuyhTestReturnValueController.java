package com.suyh5601.controller;

import com.suyh5601.argument.bind.CurrUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suyh/return")
@Slf4j
public class SuyhTestReturnValueController {

    @RequestMapping("/void")
    public void returnVoid() {
    }

    @RequestMapping("/string")
    public String returnString() {
        return "OK";
    }

    @RequestMapping("/integer")
    public Integer returnInt() {
        return 1;
    }

    @RequestMapping("/boolean")
    public Boolean returnBoolean() {
        return Boolean.TRUE;
    }

    @RequestMapping("/exception")
    public Boolean returnException() {
        throw new RuntimeException("throw suyh exception.");
    }

    @RequestMapping(value = "/object", produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrUserVo returnObject(@RequestAttribute(name = "currLoginUser", required = false) CurrUserVo userVo) {
        CurrUserVo vo = new CurrUserVo();
        vo.setId(1L);
        vo.setName("name");
        return vo;
    }
}
