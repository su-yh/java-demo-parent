package com.suyh;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Set;

/**
 * @author suyh
 * @since 2024-11-04
 */
public class ZonIds {
    public static void main(String[] args) {
        // 列出java 中所有的时区
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        if (false) {
            // 这里只列出时区
            for (String zoneId : zoneIds) {
                System.out.println(zoneId);
            }
            return;
        }
        Instant instant = Instant.now();

        for (String zoneIdKey : zoneIds) {
            ZoneId zoneId = ZoneId.of(zoneIdKey);
            ZoneOffset zoneOffset = zoneId.getRules().getOffset(instant);
            int minutes = zoneOffset.getTotalSeconds() / 60;
            System.out.println("zoneIdKey: " + zoneIdKey + ", zoneOffset: " + minutes + "(minutes)");
            String sqlInsert = String.format(
                    "INSERT INTO standard_zone (zone_id, zone_offset_seconds, show_desc, enabled) VALUES ('%s', %d, '%s', %d);",
                    zoneIdKey, zoneOffset.getTotalSeconds(), "", 0);
            System.out.println(sqlInsert);
        }
    }
}
