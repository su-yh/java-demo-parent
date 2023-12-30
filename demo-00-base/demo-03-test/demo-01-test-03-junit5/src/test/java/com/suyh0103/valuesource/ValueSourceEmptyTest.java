package com.suyh0103.valuesource;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ValueSourceEmptyTest {

    boolean isEmpty(String input) {
        return (input == null || input.length() == 0);
    }

    // run 3 times, 1 for empty, 1 for null, 1 for ""
    // 运行3次，1次为empty，1次为null，1次为""
    @ParameterizedTest(name = "#{index} - isEmpty()? {0}")
    @EmptySource
    @NullSource
    // @NullAndEmptySource  // 它是上面两个的组合注解
    @ValueSource(strings = {""})
    void test_is_empty_true(String arg) {
        Assertions.assertTrue(isEmpty(arg));
    }

    @ParameterizedTest(name = "#{index} - isEmpty()? {0}")
    @ValueSource(strings = {" ", "\n", "a", "\t"})
    void test_is_empty_false(String arg) {
        Assertions.assertFalse(isEmpty(arg));
    }

}
