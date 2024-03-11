package com.suyh5802.web.base.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.suyh5802.web.base.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author suyh
 * @since 2024-01-12
 */
@Component
@Slf4j
public class JsonPostProcessor implements BeanPostProcessor {
    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (ObjectMapper.class.isAssignableFrom(bean.getClass())) {
            log.info("init json utils.");
            assert count.incrementAndGet() == 1;

            // 对于前端而言，long 类型会有溢出的风险，这里将所有 的long 类型都处理成字符串。
            // 对于不需要计算的long 类型只需要展示，没有影响。对于需要计算的long 类型前端要自行处理。
            // 另外就是反序列化的时候一般都可以自动识别并解析成对应的long 类型数据。
            // 同时，在这里处理并添加也同样影响spring web 中的序列化.
            ObjectMapper objectMapper = (ObjectMapper) bean;
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(Long.class, NumberSerializer.instance)
                    .addSerializer(Long.TYPE, NumberSerializer.instance);
            objectMapper.registerModules(simpleModule);

            // 使用spring bean 中的ObjectMapper 初始化JsonUtils 工具类，使得全局的json 序列化与反序列化同步一致。
            JsonUtils.initMapper(objectMapper);
        }

        return bean;
    }
}
