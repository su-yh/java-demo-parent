package com.suyh.usage3001.controller;

import com.suyh.define3001.service.SomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class SomeController {

    @Resource
    private SomeService service;

    @RequestMapping(value = "/some/{param}")
    public String someHandler(@PathVariable("param") String word) {
        return service.wrap(word);
    }
}
