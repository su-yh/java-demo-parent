package com.suyh5601.controller;

import com.suyh5601.argument.bind.CurrUserVo;
import com.suyh5601.argument.bind.UserParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/test/suyh")
@Validated
@Slf4j
public class TestController5601 {
    @RequestMapping("/validation")
    public String testValidation(@UserParameter @Valid CurrUserVo userVo) {
        return userVo.getName();
    }
}
