package com.suyh;

import java.time.ZoneId;
import java.util.Set;

/**
 * @author suyh
 * @since 2024-11-04
 */
public class ZonIds {
    public static void main(String[] args) {
        // 列出java 中所有的时区
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for (String zoneId : zoneIds) {
            System.out.println(zoneId);
        }
    }
}
