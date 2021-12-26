package com.suyh13401.init;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.tomcat.TomcatMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import java.util.List;
import java.util.Set;

@Component
@EnableScheduling
@Slf4j
public class TomcatMetricRunner implements ApplicationRunner {
    @Resource
    private MeterRegistry registry;

    @Resource
    private MetricsEndpoint metricsEndpoint;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    // 这些统计想要生效，并取到相应的值，则需要添加配置项：server.tomcat.mbeanregistry.enabled=true
    // tomcat 其他配置项: 
    // server.tomcat.threads.max=100
    // server.tomcat.threads.min-spare=3
    private void tomcatMbeanServer() {
        MBeanServer mBeanServer = TomcatMetrics.getMBeanServer();
        Set<ObjectName> objectNames = mBeanServer.queryNames(getNamePattern(":type=ThreadPool,name=*"), null);
        for (ObjectName name : objectNames) {
            try {
                Object maxThreads = mBeanServer.getAttribute(name, "maxThreads");
                Object currentThreadsBusy = mBeanServer.getAttribute(name, "currentThreadsBusy");
                Object currentThreadCount = mBeanServer.getAttribute(name, "currentThreadCount");
                Object connectionCount = mBeanServer.getAttribute(name, "connectionCount");
                Object keepAliveCount = mBeanServer.getAttribute(name, "keepAliveCount");
                Object maxConnections = mBeanServer.getAttribute(name, "maxConnections");
                log.info("maxThreads: {}, currentThreadsBusy: {}, currentThreadCount: {}, " +
                                "connectionCount: {}, keepAliveCount: {}, maxConnections: {}",
                        maxThreads, currentThreadsBusy, currentThreadCount,
                        connectionCount, keepAliveCount, maxConnections);
            } catch (MBeanException | AttributeNotFoundException | InstanceNotFoundException | ReflectionException e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "*/2 * * * * ? ")
    public void temp() {
        tomcatMbeanServer();
    }

    /**
     * 参考：io.micrometer.core.instrument.binder.tomcat.TomcatMetrics#getNamePattern(java.lang.String)
     */
    private ObjectName getNamePattern(String namePatternSuffix) {
        try {
            return new ObjectName("Tomcat" + namePatternSuffix);
        } catch (MalformedObjectNameException e) {
            // should never happen
            throw new RuntimeException("Error registering Tomcat JMX based metrics", e);
        }
    }

    /**
     * 获取第一个数据信息
     */
    private void logFirstMetricInfo(String name) {
        // 参考自：io.micrometer.core.instrument.binder.tomcat.TomcatMetrics.registerThreadPoolMetrics
        MetricsEndpoint.MetricResponse metricResponse = metricsEndpoint.metric(name, null);
        if (metricResponse != null) {
            List<MetricsEndpoint.Sample> measurements = metricResponse.getMeasurements();
            if (measurements != null && !measurements.isEmpty()) {
                Double tomcatThreadsConfigMax = measurements.get(0).getValue();
                log.info("{}: {}", name, tomcatThreadsConfigMax);
            }
        }
    }
}
