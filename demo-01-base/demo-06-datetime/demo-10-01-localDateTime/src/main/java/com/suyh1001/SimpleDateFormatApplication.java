package com.suyh1001;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author suyh
 * @since 2024-05-28
 */
public class SimpleDateFormatApplication {
    public static void maint(String[] args) {
        func01();
    }

    public static void func01() {
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(zoneId));
        String formattedDate = sdf.format(timeMillis);
        System.out.println("Formatted Date: " + formattedDate);
    }
}
