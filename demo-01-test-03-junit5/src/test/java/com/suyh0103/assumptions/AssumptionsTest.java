package com.suyh0103.assumptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssumptionsTest {
    // Output: org.opentest4j.TestAbortedException: Assumption failed: assumption is not true
    @DisplayName("Run this if `assumeTrue` condition is true, else aborting this test")
    @Test
    void testOnlyOnDevEnvElseAbort() {
        // 当环境变量中有APP_MODE 且值为DEV 时，该测试用例将会运行，否则不会运行。
        Assumptions.assumeTrue("DEV".equals(System.getenv("APP_MODE")));
        Assertions.assertEquals(2, 1 + 1);
    }

    // Output: org.opentest4j.TestAbortedException: Assumption failed: Aborting test: not on developer environment
    @DisplayName("Run this if `assumeTrue` condition is true, else aborting this test (Custom Message)")
    @Test
    void testOnlyOnDevEnvElseAbortWithCustomMsg() {
        Assumptions.assumeTrue("DEV".equals(System.getenv("APP_MODE")), () -> "Aborting test: not on developer environment");
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void testAssumingThat() {

        // run these assertions always, just like normal test
        Assertions.assertEquals(2, 1 + 1);

        Assumptions.assumingThat("DEV".equals(System.getenv("APP_MODE")),
                () -> {
                    // run this only if assumingThat condition is true
                    // 仅当假设该条件为真时运行此操作
                    Assertions.assertEquals(2, 1 + 1);
                });

        // run these assertions always, just like normal test
        Assertions.assertEquals(2, 1 + 1);

    }

}
