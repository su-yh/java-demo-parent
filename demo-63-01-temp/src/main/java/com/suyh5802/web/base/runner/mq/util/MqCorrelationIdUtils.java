package com.suyh5802.web.base.runner.mq.util;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;

/**
 * @author suyh
 * @since 2023-12-28
 */
public class MqCorrelationIdUtils {
    public static long getCorrelationId() {
        return DefaultIdentifierGenerator.getInstance().nextId(null);
    }
}
