package com.suyh.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class EnDecoderUtilTest {
    @Test
    public void testMd501() {
        //md5简单加密
        String text = "i am text";
        String textMd5 = EnDecoderUtil.md5Encrypt(text);
        log.info("md5({}): {}", text, textMd5);
    }
}
