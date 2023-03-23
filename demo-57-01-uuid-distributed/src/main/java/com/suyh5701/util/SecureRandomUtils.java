package com.suyh5701.util;

import java.security.SecureRandom;

public final class SecureRandomUtils {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String buildVerifyCode() {
        int val = RANDOM.nextInt(800000) + 100000;
        return val + "";
    }
}
