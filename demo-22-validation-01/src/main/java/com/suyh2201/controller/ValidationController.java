package com.suyh2201.controller;

import com.suyh2201.entity.ChildEntity;
import com.suyh2201.entity.ParentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * __    限制	说明
 * 注解： @Null 限制只能为null
 * 注解： @NotNull 限制必须不为null
 * 注解： @AssertFalse 限制必须为false
 * 注解： @AssertTrue 限制必须为true
 * 注解： @DecimalMax(value) 限制必须为一个不大于指定值的数字
 * 注解： @DecimalMin(value) 限制必须为一个不小于指定值的数字
 * 注解： @Digits(integer,fraction) 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction
 * 注解： @Future 限制必须是一个将来的日期
 * 注解： @Max(value) 限制必须为一个不大于指定值的数字
 * 注解： @Min(value) 限制必须为一个不小于指定值的数字
 * 注解： @Past 限制必须是一个过去的日期
 * 注解： @Pattern(value) 限制必须符合指定的正则表达式
 * 注解： @Size(max,min) 限制字符长度必须在min到max之间
 * 注解： @Past 验证注解的元素值（日期类型）比当前时间早
 * 注解： @NotEmpty 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0）
 * 注解： @NotBlank 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格
 * 注解： @Email 验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式
 */
@Validated  // 需要这个注解
@RestController
@Slf4j
public class ValidationController {

    @RequestMapping(value = "/get/child/entity", method = RequestMethod.GET)
    public ChildEntity getChildEntityByGet(
            @Valid ChildEntity childEntity) {
        log.info("getChildEntity: {}", childEntity);
        return childEntity;
    }

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
    public String getString(@RequestParam("name") @NotBlank String name) {
        log.info("getString, name: {}", name);
        return name;
    }
}
