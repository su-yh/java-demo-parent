package com.suyh5802.web.base.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author suyh
 * @since 2023-12-07
 */
@Component
@Slf4j
public class MessageSourceTestRunner implements ApplicationRunner {
    @Resource
    private MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String message = messageSource.getMessage("suyh.temp", null, LocaleContextHolder.getLocale());
        System.out.println(message);
        message = messageSource.getMessage("suyh.notExist", null, "没有配置", LocaleContextHolder.getLocale());
        System.out.println(message);
    }
}
