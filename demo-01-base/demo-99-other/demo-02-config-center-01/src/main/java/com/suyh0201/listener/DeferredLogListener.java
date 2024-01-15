package com.suyh0201.listener;

import com.suyh0201.environment.ConfigCenterEnvironmentPostProcessor;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;

/**
 * @author suyh
 * @since 2024-01-13
 */
public class DeferredLogListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(@NonNull ApplicationEnvironmentPreparedEvent event) {
        /*
         * 如下的日志代码要生效，则需要它的执行时机在 LoggingApplicationListener.onApplicationEnvironmentPreparedEvent 之后
         * 这里由于order 的值是(Ordered.HIGHEST_PRECEDENCE + 10) 而LoggingApplicationListener.DEFAULT_ORDER是(Ordered.HIGHEST_PRECEDENCE + 20)
         * 使得当前的优先级高于LoggingApplicationListener，所以如果在这里添加则当前日志系统中的相关日志将不会被正常打印出来。
         * 所以这个LOGGER 的日志要通过其他方式处理，或者监听的事件改一个在ApplicationEnvironmentPreparedEvent 之后的，又或者另外实现一个监听器
         * 在那个监听器里面实现这个LOGGER。
         */
        ConfigCenterEnvironmentPostProcessor.LOGGER.replayTo(ConfigCenterEnvironmentPostProcessor.class);
    }
}
