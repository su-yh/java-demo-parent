package com.suyh5303.confIg;

import com.suyh5303.listener.ZkStarterListener;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DemoConfiguration5303 {
    @ConditionalOnMissingBean(CuratorFramework.class)
    @Bean
    public CuratorFramework curatorFramework() {
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(2000, 3);
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString("docker:2181")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(3000)
                .namespace("/dev-demo")
                .retryPolicy(retry);
        return builder.build();
    }

    @Bean(initMethod = "start")
    public ZkStarterListener zkStarterListener(CuratorFramework client) {
        return new ZkStarterListener(client);
    }
}
