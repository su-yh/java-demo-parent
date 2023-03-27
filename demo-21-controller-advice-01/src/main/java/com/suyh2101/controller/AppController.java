package com.suyh2101.controller;

import com.suyh2101.exception.SuyhException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AppController {
    @GetMapping("/req/exception")
    public String getException() throws Exception {
        throw new Exception();
    }

    @GetMapping("/req/runtime/exception")
    public String getRuntimeException() {
        throw new RuntimeException("run time exception");
    }

    @GetMapping("/req/suyh/exception")
    public String getSuyhException() {
        throw new SuyhException("suyh exception");
    }

    @GetMapping("/req/normal")
    public String getNonException() {
        return "no exception, result is ok.";
    }

    @GetMapping("/req/header")
    public String headerMissingException(@RequestHeader("suyhHeader") String suyhHeader) {
        return suyhHeader;
    }
}
