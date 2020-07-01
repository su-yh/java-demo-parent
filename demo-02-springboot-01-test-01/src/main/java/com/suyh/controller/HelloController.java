package com.suyh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
