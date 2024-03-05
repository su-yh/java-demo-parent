package com.suyh66;

import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2024-03-05
 */
@Component
public class TestBean {

    private TestBean self() {
        return SpringUtils.getBean(getClass());
    }

    public void hello() {
        System.out.println("hello");
        self().hi();
    }

    public void hi() {
        System.out.println("hi");
    }
}
