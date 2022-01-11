package com.suyh.demo.component;


import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;

import javax.annotation.PostConstruct;

public class SampleBean {
    private final Counter counter;
    private final Counter counter2;
    private final Summary summary;
    private final MyCollector myCollector;

    public SampleBean(CollectorRegistry collectorRegistry) {
        counter = Counter.build()
                .name("request_counter")
                .help("request.counter.helper")
                .labelNames("path", "method", "code")
                .namespace("application_name")
                .register(collectorRegistry);

        counter2 = Counter.build()
                .name("request_counter_2")
                .help("request.counter_2.helper")
                .namespace("application_name")
                .register(collectorRegistry);

        summary = Summary.build()
                .name("request_summary")
                .help("request_summary_helper")
                .quantile(0.5, 0.05)
                .quantile(0.9, 0.01)
                .register(collectorRegistry);

        myCollector = new MyCollector().register(collectorRegistry);
    }

    @PostConstruct
    public void init() {
        this.counter.labels("path-value", "method-value", "code-value").inc();
        this.counter2.inc();

        summary.observe(1.0);
    }
}
