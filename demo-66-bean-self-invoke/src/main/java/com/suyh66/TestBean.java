package com.suyh66;

import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2024-03-05
 */
@Component
public class TestBean {


    public void hello() {
        System.out.println("hello");
        hi();
    }

    public void hi() {
        System.out.println("hi");
    }
}
