package com.suyh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter")
@Slf4j
public class FilterController {

    @GetMapping("/name")
    public String getName(@RequestParam("name") String name) {
        log.info("name: {}", name);
        return "filter: " + name;
    }
}
