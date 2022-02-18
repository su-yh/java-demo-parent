package com.suyh2301.pointcut.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PointcutService {
    public void sayHello() {
        log.info("NormalService.sayHello");
    }

    public void execException() {
        log.info("NormalService.execException");
        throw new RuntimeException("notmal exception");
    }
}
