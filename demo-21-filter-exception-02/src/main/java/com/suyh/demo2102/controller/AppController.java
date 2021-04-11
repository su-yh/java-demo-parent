package com.suyh.demo2102.controller;

import com.suyh.demo2102.exception.SuyhException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/filter")
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
