package com.suyh0103.timeout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TimeOutExample2 {

    // timed out after 5 seconds
    @Test
    void test_timeout_fail() {
        // 期望执行时间在5 秒内结束，实际是10 秒，所以测试失败。
        //  Assertions.assertTimeout(Duration.ofSeconds(5), () -> delaySecond(10)); // this will fail

        // 期望执行时间在5 秒内结束，实际是1 秒，所以通过测试
        Assertions.assertTimeout(Duration.ofSeconds(5), () -> delaySecond(1)); // pass
    }

    void delaySecond(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
