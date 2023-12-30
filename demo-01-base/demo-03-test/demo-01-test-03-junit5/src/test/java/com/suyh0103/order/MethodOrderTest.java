package com.suyh0103.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * 按@{@link Order}(value) 注解的顺序进行顺序执行测试用例
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodOrderTest {

    @Test
    void test0() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    @Order(3)
    void test1() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    @Order(1)
    void test2() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    @Order(2)
    void test3() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void test4() {
        Assertions.assertEquals(2, 1 + 1);
    }

}
