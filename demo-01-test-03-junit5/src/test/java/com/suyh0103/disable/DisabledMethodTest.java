package com.suyh0103.disable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledMethodTest {
    @Disabled("Disabled until CustomerService is up!")
    @Test
    void testCustomerServiceGet() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void test3Plus3() {
        Assertions.assertEquals(6, 3 + 3);
    }
}
