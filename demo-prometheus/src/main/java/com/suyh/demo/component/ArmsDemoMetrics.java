package com.suyh.demo.component;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.BaseUnits;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.core.lang.NonNull;

import java.util.Random;

public class ArmsDemoMetrics implements MeterBinder {
    private final Random random = new Random();

    /**
     * 这里builder 里面的name 是提供给metrics 接口使用的，
     * 如果要使用prometheus 来访问，则需要将'.' 转换成'_'，同时会有后缀，
     * 具体查看 {@link Meter.Id#getConventionName(NamingConvention)}
     * 以及 {@link NamingConvention#snakeCase}
     */
    @Override
    public void bindTo(@NonNull MeterRegistry registry) {
        Gauge.builder("com.suyh.test.demo.metrics", this::theNumberValue)
                // tag 是以key-value 的形式存在的
                .tag("tags-key01", "tags-value01")
                .tag("tags-key02", "tags-value02")
                .description("suyh 测试使用")
                // 单位就是一个字符串，好像只是给人看的，没有实际计算。
                .baseUnit(BaseUnits.BYTES)
                .register(registry);

        Gauge.builder("com.suyh.test.demo02", this::theNumberValue02)
                .description("suyh 测试第二个测试")
                .baseUnit("other")
                .register(registry);
    }

    public Number theNumberValue() {
        return random.nextInt(100);
    }

    private Number theNumberValue02() {
        return random.nextInt(10);
    }
}
