package com.suyh.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigInteger;

@Slf4j
public class EncryptDecryptUtilTest {
    @Test
    public void testMd501() {
        //md5简单加密
        String text = "i am text";
        String textMd5 = EncryptDecryptUtil.md5Encrypt(text);
        log.info("md5({}): {}", text, textMd5);
    }

    @Test
    public void testMd502() {
        String text = "what is this";
        byte[] bytes = EncryptDecryptUtil.md5Encrypt(text.getBytes());
        String hexStr = RadixConvertUtils.bytes2Hex(bytes);
        RadixConvertUtils.hex2Bytes(hexStr);
        byte[] bytesRes = RadixConvertUtils.hex2Bytes(hexStr);
        log.info("bytes: {}", hexStr);
        BigInteger bigInteger = new BigInteger(bytes);
        log.info("md5({}): {}", text, bigInteger.toString(16));
    }
}
