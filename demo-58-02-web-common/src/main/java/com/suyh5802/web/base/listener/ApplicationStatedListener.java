package com.suyh5802.web.base.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.suyh5802.web.base.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;

/**
 * @author suyh
 * @since 2024-01-12
 */
@Slf4j
public class ApplicationStatedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
        log.info("suyh - [listener] init object mapper.");
        ConfigurableApplicationContext context = event.getApplicationContext();
        ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

        // 对于前端而言，long 类型会有溢出的风险，这里将所有 的long 类型都处理成字符串。
        // 对于不需要计算的long 类型只需要展示，没有影响。对于需要计算的long 类型前端要自行处理。
        // 另外就是反序列化的时候一般都可以自动识别并解析成对应的long 类型数据。
        // 同时，在这里处理并添加也同样影响spring web 中的序列化.
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, NumberSerializer.instance)
                        .addSerializer(Long.TYPE, NumberSerializer.instance);
        objectMapper.registerModules(simpleModule);

        // 使用spring bean 中的ObjectMapper 初始化JsonUtils 工具类，使得全局的json 序列化与反序列化同步一致。
        JsonUtils.initMapper(objectMapper);
    }
}
