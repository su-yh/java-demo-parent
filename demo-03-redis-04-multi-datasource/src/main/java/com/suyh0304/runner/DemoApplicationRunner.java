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
    private final TextCacheRedis textCacheRedis;

    // ObjectProvider<RedisStandaloneConfiguration>
    public DemoApplicationRunner(@Qualifier("otherTextCacheRedis") TextCacheRedis textCacheRedis) {
        this.textCacheRedis = textCacheRedis;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String key = TextCacheRedis.buildRedisKey("clusterConfiguration");
        textCacheRedis.setText(key, "value", Duration.ofMinutes(1));
        String text = textCacheRedis.getText(key);
        log.info("value: {}", text);
    }
}
