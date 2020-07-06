package com.suyh.controller;

import com.suyh.exception.SuyhException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AppController {
    @RequestMapping("/req/exception")
    public String getException() throws Exception {
        throw new Exception();
    }

    @RequestMapping("/req/runtime/exception")
    public String getRuntimeException() {
        throw new RuntimeException("run time exception");
    }

    @RequestMapping("/req/suyh/exception")
    public String getSuyhException() {
        throw new SuyhException("suyh exception");
    }

    @RequestMapping("/req/normal")
    public String getNonException() {
        return "no exception, result is ok.";
    }
}
