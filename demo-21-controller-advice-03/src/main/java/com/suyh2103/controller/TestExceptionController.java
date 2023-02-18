package com.suyh2103.controller;

import com.suyh2103.exception.SuyhException;
import com.suyh2103.vo.ErrorCode;
import com.suyh2103.vo.SuyhResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestExceptionController {
    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public SuyhResult<Boolean> testException(@RequestParam("type") Integer type) {
        if (type == 1) {
            throw new SuyhException(ErrorCode.ERROR_SUYH);
        }

        if (type == 2) {
            throw new RuntimeException("runtime exception");
        }

        return SuyhResult.ofSuccess();
    }
}
