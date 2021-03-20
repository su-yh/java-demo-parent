package com.suyh.demo3601.controller;

import com.suyh.demo3601.service.CustomEventPublishService;
import com.suyh.listeners.custom.CustomEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CustomEventController {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Resource
    private CustomEventPublishService publishService;

    /**
     * 事件发布方式，第一种：使用 ApplicationEventPublisher
     */
    @RequestMapping("/custom/event/publish01")
    public String publishCustomEvent01() {
        System.out.println("publish01 before");
        eventPublisher.publishEvent(new CustomEvent("publish01"));
        System.out.println("publish01 finished.");
        return "OK";
    }

    /**
     * 事件发布方式，第二种：同样使用 ApplicationEventPublisher
     */
    @RequestMapping("/custom/event/publish02")
    public String publishCustomEvent02() {
        System.out.println("publish02 before");
        publishService.publishEvent(new CustomEvent("publish02"));
        System.out.println("publish02 finished.");
        return "OK";
    }
}
