package com.suyh3902.component;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@Slf4j
public class ServiceRegistry implements InitializingBean {
    // 使用nacos 提供的注解获取NamingService 实例，并不一定是Spring Bean 对象。
    // 如果是spring cloud 工程，使用该方法是获取不到实例的。需要借助(spring bean): NacosServiceManager 和NacosDiscoveryProperties
    @NacosInjected
    private NamingService namingService;

    @Value("${server.port}")
    private int listenerPort;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            // 获取到本地主机网络地址
            InetAddress localHost = InetAddress.getLocalHost();
            namingService.registerInstance(applicationName, localHost.getHostAddress(), listenerPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
