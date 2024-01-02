package com.suyh0103.valuesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.EnumSet;

public class EnumSourceTest {

    enum Size {
        XXS, XS, S, M, L, XL, XXL, XXXL;
    }

    /**
     * 这个方法的参数，会将上面的每一个枚举值依次传入。
     */
    @ParameterizedTest
    @EnumSource(Size.class)
    void test_enum(Size size) {
        Assertions.assertNotNull(size);
    }

    /**
     * 这个方法会将names 里面的对应枚举值依次传入
     */
    @ParameterizedTest(name = "#{index} - Is size contains {0}?")
    @EnumSource(value = Size.class, names = {"L", "XL", "XXL", "XXXL"})
    void test_enum_include(Size size) {
        Assertions.assertTrue(EnumSet.allOf(Size.class).contains(size));
    }

    /**
     * 这个方法会将指定的这些排除之后，剩余的依次传入
     */
    // Size = M, L, XL, XXL, XXXL
    @ParameterizedTest
    @EnumSource(value = Size.class, mode = EnumSource.Mode.EXCLUDE, names = {"XXS", "XS", "S"})
    void test_enum_exclude(Size size) {
        EnumSet<Size> excludeSmallSize = EnumSet.range(Size.M, Size.XXXL);
        Assertions.assertTrue(excludeSmallSize.contains(size));
    }

}
