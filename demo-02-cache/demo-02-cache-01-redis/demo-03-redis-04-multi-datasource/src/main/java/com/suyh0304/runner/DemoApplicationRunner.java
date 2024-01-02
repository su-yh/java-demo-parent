package com.suyh0304.runner;

import com.suyh0304.redis.TextCacheRedis;
import com.suyh0304.tmp.SuyhProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
public class DemoApplicationRunner implements ApplicationRunner {
    private final TextCacheRedis otherTextCacheRedis;
    private final TextCacheRedis businessTextCacheRedis;
    private final SuyhProperties suyhProperties;

    public DemoApplicationRunner(
            @Qualifier("businessTextCacheRedis") TextCacheRedis businessTextCacheRedis,
            @Qualifier("otherTextCacheRedis") TextCacheRedis otherTextCacheRedis,
            // ObjectProvider 允许SuyhProperties 这个bean 不存在
            ObjectProvider<SuyhProperties> providerProperties,
            ObjectProvider<TextCacheRedis> providerTextRedis) {
        this.otherTextCacheRedis = otherTextCacheRedis;
        this.businessTextCacheRedis = businessTextCacheRedis;
        // 获取实际的SuyhProperties bean 对象，如果bean 不存在则为null
        this.suyhProperties = providerProperties.getIfAvailable();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String key = TextCacheRedis.buildRedisKey("clusterConfiguration");
        otherTextCacheRedis.setText(key, "value", Duration.ofMinutes(1));
        businessTextCacheRedis.setText(key, "value", Duration.ofMinutes(1));
        String text = otherTextCacheRedis.getText(key);
        log.info("value: {}", text);

        if (suyhProperties == null) {
            log.info("suyhProperties is null");
        }
    }
}
