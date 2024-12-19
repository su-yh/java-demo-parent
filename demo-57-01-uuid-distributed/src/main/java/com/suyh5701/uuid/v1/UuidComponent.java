package com.suyh5701.uuid.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author suyh
 * @since 2024-06-24
 */
@Component
@Slf4j
public class UuidComponent {
    private final AtomicLong uuidBase = new AtomicLong(0L);

    @PostConstruct
    public void init() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime baseTime = LocalDateTime.of(2024, 12, 1, 0, 0, 0);
        LocalDateTime nowTime = LocalDateTime.now();
        long epochMilliBase = baseTime.atZone(zoneId).toInstant().toEpochMilli();
        long epochMilliNow = nowTime.atZone(zoneId).toInstant().toEpochMilli();
        long baseNumberMilli = epochMilliNow - epochMilliBase;
        long baseValue = (baseNumberMilli << 10);
        uuidBase.set(baseValue);
    }

    public long uuidLong() {
        return uuidBase.incrementAndGet();
    }

    // 字符串长度至少为12 个宽度
    private final static int UUID_HEX_LEN = 12;

    // 12 个字符长度
    public String uuidStr() {
        long uuidLong = uuidLong();

        String uuidHex = Long.toHexString(uuidLong);
        int lengthToPad = UUID_HEX_LEN - uuidHex.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lengthToPad; i++) {
            sb.append('0');
        }
        sb.append(uuidHex);
        return sb.toString();
    }
}
