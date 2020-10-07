package com.suyh.activiti.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.springframework.stereotype.Component;

/**
 * 实现一个执行时间的拦截器
 */
@Slf4j
@Component
public class DurationCommandInterceptor extends AbstractCommandInterceptor {
    @Override
    public <T> T execute(CommandConfig config, Command<T> command) {
        long start = System.currentTimeMillis();
        try {
            // 这里必须把返回值返回出来，否则下一个执行链条就会出现空指针异常
            return this.getNext().execute(config, command);
        } finally {
            long last = System.currentTimeMillis();
            long duration = last - start;
            log.info("{} 执行时长 {} 毫秒", command.getClass().getSimpleName(), duration);
        }
    }
}
