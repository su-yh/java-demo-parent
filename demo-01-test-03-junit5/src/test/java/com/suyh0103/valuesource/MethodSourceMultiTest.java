package com.suyh0103.valuesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MethodSourceMultiTest {

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int length, List<String> list) {
        Assertions.assertTrue(str.length() > 0);
        Assertions.assertEquals(length, list.size());
    }

    /**
     * 构建出多组参数，依次作为上面的测试方法的参数传入。
     */
    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.arguments("abc", 3, Arrays.asList("a", "b", "c")),
                Arguments.arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

}
