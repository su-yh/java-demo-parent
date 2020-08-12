package com.suyh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    private static final Logger logger
            = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hi")
    public String hi(String name) {
        logger.debug("debug log.");
        logger.info("info log.");
        logger.warn("warn log.");

        return "hello " + name;
    }

    @RequestMapping("/hi/headers")
    public String headers(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestParam("paramName") String paramName,
            @RequestBody SecurityProperties.User user) {
        logger.info("header token: {}", token);
        logger.info("paramName: {}, userName: {}", paramName, user.getName());
        return "Headers-token: " + token;
    }
}
