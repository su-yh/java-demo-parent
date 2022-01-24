package com.suyh.demo.component;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.BaseUnits;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.core.lang.NonNull;

import java.util.Random;

/**
 * 实现接口MeterBinder 则可以通过访问: http://localhost:6557/gov/app/demo/arms/actuator/prometheus?includedNames=arms_system_pass_qps_second&includedNames=arms_system_rt_second
 * 的方式来获取相关数据信息了。
 * 在 prometheus 的服务端。
 * 对于实现MeterBinder 的bean 对象，只有在应用启用的时候来注册一个指标到Metric, prometheus 可以取到所有的Metric 中的指标数据。
 * 但是如果要实时动态添加不同的指标到Prometheus ，则需要实现自定义Collector 才可以。
 */
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
