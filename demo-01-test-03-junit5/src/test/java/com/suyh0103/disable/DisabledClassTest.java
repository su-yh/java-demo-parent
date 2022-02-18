package com.suyh0103.disable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Disabled until bug #2019 has been fixed!")
public class DisabledClassTest {

    @Test
    void test1Plus1() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void test2Plus2() {
        Assertions.assertEquals(4, 2 + 2);
    }

}
