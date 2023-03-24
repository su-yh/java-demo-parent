package com.suyh0304.runner;

import com.suyh0304.redis.TextCacheRedis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DemoApplicationRunner implements ApplicationRunner {
    private final TextCacheRedis textCacheRedis;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        textCacheRedis.setText("suyh-test-temp", "value", Duration.ofMinutes(1));
//        String text = textCacheRedis.getText("suyh-test-temp");
//        log.info("value: {}", text);
    }
}
