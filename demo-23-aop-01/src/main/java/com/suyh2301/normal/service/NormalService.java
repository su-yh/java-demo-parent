package com.suyh2301.normal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NormalService {
    public void sayHello() {
        log.info("NormalService.sayHello");
    }
}
