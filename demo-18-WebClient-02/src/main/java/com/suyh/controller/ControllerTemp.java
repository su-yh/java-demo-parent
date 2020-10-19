package com.suyh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/suyh")
public class ControllerTemp {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String getInfo() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "getInfo";
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public String getUser() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "getUserInfo";
    }
}
