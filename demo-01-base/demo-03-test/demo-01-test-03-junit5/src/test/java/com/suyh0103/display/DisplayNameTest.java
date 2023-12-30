package com.suyh0103.display;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * DisplayName 注解，就是简单的显示测试使用的名称。
 */
@DisplayName("I'm a Test Class")
public class DisplayNameTest {

    @DisplayName("Test with spaces, expected ok")
    @Test
    void test_spaces_ok() {
    }

    @DisplayName("Test with spaces, expected failed")
    @Test
    void test_spaces_fail() {
    }

}
