package com.suyh5701.util;

import com.suyh5701.component.uuid.v2.UuidComponent;
import lombok.Setter;

public final class UuidUtils {
    @Setter
    private static UuidComponent uuidComponent;

    public static long generateUuidLong() {
        return uuidComponent.generateUuidLong();
    }

    public static String generateUuidString() {
        return uuidComponent.generateUuidString();
    }
}
