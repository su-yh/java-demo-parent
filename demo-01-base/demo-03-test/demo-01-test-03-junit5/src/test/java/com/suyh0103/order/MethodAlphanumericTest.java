package com.suyh0103.order;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class MethodAlphanumericTest {

    @Test
    void testZ() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void testA() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void testY() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void testE() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void testB() {
        Assertions.assertEquals(2, 1 + 1);
    }

}
