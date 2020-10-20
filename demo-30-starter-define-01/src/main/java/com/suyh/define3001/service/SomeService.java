package com.suyh.define3001.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 核心业务类
 */
@AllArgsConstructor
@Slf4j
public class SomeService {
    /**
     * 前缀
     */
    private String before;
    /**
     * 后缀
     */
    private String after;

    /**
     * 核心业务代码
     */
    public String wrap(String word) {
        return before + word + after;
    }
}
