package com.suyh.demo3602.controller;

import com.suyh.demo3602.service.EventPublishService;
import com.suyh.listeners.custom.CustomEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CustomEventController {

    @Resource
    private EventPublishService publishService;

    @RequestMapping("/custom/event/publish")
    public String publishCustomEvent() {
        System.out.println("publish before");
        publishService.publishEvent(new CustomEvent("publish"));
        System.out.println("publish finished.");
        return "OK";
    }
}
