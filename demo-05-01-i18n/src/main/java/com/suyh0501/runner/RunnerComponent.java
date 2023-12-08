package com.suyh0501.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2023-12-07
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RunnerComponent implements ApplicationRunner {
    private final MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String message = messageSource.getMessage("suyh.key", null, "没有找到，使用默认值", LocaleContextHolder.getLocale());
        System.out.println("message: " + message);
    }
}
