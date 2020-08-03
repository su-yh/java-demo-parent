package com.suyh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServiceBean {
    private int count = 0;

    public String sayHello(String name) {
        return "Hello " + name;
    }

    public int getCount() {
        return ++count;
    }
}
