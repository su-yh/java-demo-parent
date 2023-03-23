package com.suyh5701.util;

import com.suyh5701.component.util.MmUuidComponent;
import lombok.Setter;

public final class MmUuidUtils {
    @Setter
    private static MmUuidComponent uuidComponent;

    public static long generateUuidLong() {
        return uuidComponent.generateUuidLong();
    }

    public static String generateUuidString() {
        return uuidComponent.generateUuidString();
    }
}
