package com.suyh0103.valuesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValueSourceTest {

    /**
     * {@link ParameterizedTest} 就是一个显示名称吧
     */
    // This test will run 3 times with different arguments
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void test_int_arrays(int arg) {
        Assertions.assertTrue(arg > 0);
    }

    /**
     *
     * @param arg
     */
    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(ints = {1, 2, 3})
    void test_int_arrays_custom_name(int arg) {
        Assertions.assertTrue(arg > 0);
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(strings = {"apple", "banana", "orange"})
    void test_string_arrays_custom_name(String arg) {
        Assertions.assertTrue(arg.length() > 1);
    }

}
