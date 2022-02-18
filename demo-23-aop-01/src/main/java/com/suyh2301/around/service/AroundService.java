package com.suyh2301.around.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AroundService {
    public void sayHelloVoid() {
        log.info("AroundService.sayHelloVoid");
    }

    public void sayHelloString(String msg) {
        log.info("AroundService.sayHelloString({})", msg);
    }

    public String getHelloVoid() {
        log.info("AroundService.getHelloVoid");
        return "getHelloVoid";
    }

    public void sayHelloException() {
        log.info("AroundService.sayHelloException");
        throw new RuntimeException("sayHelloException is OK");
    }
}
