package com.suyh4401.controller;

import com.suyh4401.bind.annotation.RequestPropertiesBody;
import com.suyh4401.vo.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试自定义参数解析器
 */
@RestController
@RequestMapping("/suyh")
public class UserRestController {
    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public String methodTest2(@RequestBody Person person) {
        return "name is: " + person.getName() + ", age is " + person.getAge();
    }

    @RequestMapping(value = "/test3", method = RequestMethod.POST)
    public String methodTest3(@RequestPropertiesBody Person person) {
        return "name is: " + person.getName() + ", age is " + person.getAge();
    }
}