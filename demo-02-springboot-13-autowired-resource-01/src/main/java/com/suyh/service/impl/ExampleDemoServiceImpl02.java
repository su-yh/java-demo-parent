package com.suyh.service.impl;

import com.suyh.service.ExampleDemoService;
import org.springframework.stereotype.Service;

@Service
public class ExampleDemoServiceImpl02 implements ExampleDemoService {
    @Override
    public String getClassName() {
        return "Example02";
    }
}
