package com.suyh.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtils {
    public static void func() {
        LocalDateTime now = LocalDateTime.now();
        // 带时区的时间
        // 使用当前时区的时间减5 天时间
        ZonedDateTime minusTime = now.minusDays(5).atZone(ZoneId.systemDefault());
    }
}
