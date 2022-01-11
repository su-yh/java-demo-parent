
package com.suyh.demo.component;

import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 自定义一个 prometheus 的Collector
 */
@Slf4j
public class MyCollector extends Collector {
    /**
     * 自定义prometheus 的收集器，每次访问接口(http://localhost:6557/context-path/actuator/prometheus) 时
     * 都会调用 {@link MyCollector#collect()} 方法。
     * 所以我们需要将统计数据放在{@link MyCollector#collect()} 方法中实现。
     *
     * @author sWX5327794
     * @since 2022-01-06
     */
    @Override
    public List<MetricFamilySamples> collect() {
        log.info("============custom defined collector===================");
        List<MetricFamilySamples> mfs = new ArrayList<>();
        // With no labels.
        mfs.add(new GaugeMetricFamily("my_gauge", "help", 42));
        // With labels
        List<String> labelNames = Arrays.asList("labelname", "application");
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("my_other_gauge", "help", labelNames);
        // 这里的第一个参数是对应labelNames，数量必须要相同，否则会异常。
        labeledGauge.addMetric(Arrays.asList("foo", "suyh-foo"), new Random().nextInt(5));
        labeledGauge.addMetric(Arrays.asList("bar", "suyh-bar"), new Random().nextInt(5));
        mfs.add(labeledGauge);
        return mfs;
    }
}
