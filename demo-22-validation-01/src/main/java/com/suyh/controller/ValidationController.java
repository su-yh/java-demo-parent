package com.suyh.controller;

import com.suyh.entity.ChildEntity;
import com.suyh.entity.ParentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@Slf4j
public class ValidationController {

    @PostMapping("/get/child/entity")
    public ChildEntity getChildEntity(
            @RequestBody @Valid ChildEntity childEntity) {
        log.info("getChildEntity: {}", childEntity);
        return childEntity;
    }

    // 可以嵌套校验
    @PostMapping("/get/parent/entity")
    public ParentEntity getParentEntity(
            @RequestBody @Valid ParentEntity parentEntity) {
        log.info("getParentEntity: {}", parentEntity);
        return parentEntity;
    }

    // 普通类型参数校验
    @GetMapping("/get/name")
    public String getString(@RequestParam("name") @NotNull String name) {
        log.info("getString, name: {}", name);
        return name;
    }
}
