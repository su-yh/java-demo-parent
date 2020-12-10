package com.suyh2902.controller;

import com.suyh.dto.ResultSingle;
import com.suyh2902.entity.FormPropertyTemplateEntity;
import com.suyh2902.repository.FormProperTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/suyh/custom/jpa")
@Slf4j
public class CustomJpaController {
    @Resource
    private FormProperTemplateRepository repository;

    @RequestMapping(value = "/custom", method = RequestMethod.GET)
    public ResultSingle<String> customMethod() {
        repository.someCustomMethod(null);
        return new ResultSingle<>("OK");
    }

    @RequestMapping(value = "/custom/save", method = RequestMethod.GET)
    public ResultSingle<String> customSave() {
        FormPropertyTemplateEntity result = repository.save(null);
        log.info("result: {}", result);

        return new ResultSingle<>();
    }
}
