package com.suyh0103.timeout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class TimeOutExample1 {

    // timed out after 5 seconds
    // 如果执行时间超过5 秒则失败
    @BeforeEach
    @Timeout(5)
    void setUpDB() throws InterruptedException {
        // TimeUnit.SECONDS.sleep(10);
    }

    // timed out after 500 miliseconds
    // 如果该方法的执行时间超过500 ms 则失败
    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void test_this() {
    }

}
