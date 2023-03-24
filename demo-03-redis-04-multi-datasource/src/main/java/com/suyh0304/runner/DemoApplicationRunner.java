package com.suyh0304.runner;

import com.suyh0304.redis.TextCacheRedis;
import lombok.extern.slf4j.Slf4j;
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

    // ObjectProvider<RedisStandaloneConfiguration>
    public DemoApplicationRunner(
            @Qualifier("businessTextCacheRedis") TextCacheRedis businessTextCacheRedis,
            @Qualifier("otherTextCacheRedis") TextCacheRedis otherTextCacheRedis) {
        this.otherTextCacheRedis = otherTextCacheRedis;
        this.businessTextCacheRedis = businessTextCacheRedis;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String key = TextCacheRedis.buildRedisKey("clusterConfiguration");
        otherTextCacheRedis.setText(key, "value", Duration.ofMinutes(1));
        businessTextCacheRedis.setText(key, "value", Duration.ofMinutes(1));
        String text = otherTextCacheRedis.getText(key);
        log.info("value: {}", text);
    }
}
