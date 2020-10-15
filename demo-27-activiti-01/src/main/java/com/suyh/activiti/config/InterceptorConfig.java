package com.suyh.activiti.config;

import com.suyh.activiti.interceptor.DurationCommandInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Configuration
@Component
@Slf4j
public class InterceptorConfig extends ProcessEngineConfigurationImpl {
    @Resource
    private DurationCommandInterceptor interceptor;

    @Override
    public CommandInterceptor createTransactionInterceptor() {
        log.info("这个的触发时间到底在哪里呀，启动的时候没有触发");
        return interceptor;
    }

}
