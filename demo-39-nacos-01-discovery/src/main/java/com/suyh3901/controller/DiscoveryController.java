package com.suyh3901.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/discovery")
@Slf4j
public class DiscoveryController {

    // 使用nacos 提供的注解获取NamingService 实例，并不一定是Spring Bean 对象。
    // 如果是spring cloud 工程，使用该方法是获取不到实例的。需要借助(spring bean): NacosServiceManager 和NacosDiscoveryProperties
    @NacosInjected
    private NamingService namingService;

    // 访问：http://localhost.huawei.com:13901/discovery/get?serviceName=msc-test-suyh-temp-service
    // 总是忘记有个参数
    @RequestMapping(value = "/get", method = GET)
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        // 获取指定服务名称的所有服务列表
        List<Instance> serviceInstance = namingService.getAllInstances(serviceName);
        if (serviceInstance == null) {
            serviceInstance = new ArrayList<>();
        }
        for (Instance instance : serviceInstance) {
            log.info("instance: {}", JSON.toJSONString(instance));
        }
        return serviceInstance;
    }
}
