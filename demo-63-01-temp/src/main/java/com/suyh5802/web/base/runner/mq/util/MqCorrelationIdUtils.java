package com.suyh5802.web.base.runner.mq.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author suyh
 * @since 2023-12-28
 */
public class MqCorrelationIdUtils {
    private static final AtomicLong correlationId = new AtomicLong();

    static {
        long nanoTime = System.nanoTime();
        correlationId.set(nanoTime);
    }

    public static long getCorrelationId() {
        return correlationId.getAndIncrement();
    }
}
