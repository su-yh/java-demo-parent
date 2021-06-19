package com.suyh3903.config;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
public class BeansConfiguration {
    @Bean("nacosConfigListener")
    public Executor nacosConfigListener() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(10000);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("nacosConfigListener-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean
    public ConfigService nacosConfigService() throws Exception {
        String serverAddr = "localhost:8848";
        // 名字空间ID
        String namespace = "public";

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        if (!StringUtils.isEmpty(namespace)) {
            properties.put(PropertyKeyConst.NAMESPACE, namespace);
        }
        return ConfigFactory.createConfigService(properties);
    }
}
