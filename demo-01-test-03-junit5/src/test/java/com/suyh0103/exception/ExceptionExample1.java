package com.suyh0103.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionExample1 {

    @Test
    void test_exception() {
        // 期望后面的执行单元抛出 ArithmeticException 的异常
        ArithmeticException exception = Assertions.assertThrows(
                ArithmeticException.class, () -> divide(1, 0));

        Assertions.assertEquals("/ by zero", exception.getMessage());

        Assertions.assertTrue(exception.getMessage().contains("zero"));

    }

    int divide(int input, int divide) {
        return input / divide;
    }
}