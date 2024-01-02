package com.suyh.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Base64;

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
        log.info("bytes: {}", hexStr);
        BigInteger bigInteger = new BigInteger(bytes);
        log.info("md5({}): {}", text, bigInteger.toString(16));
    }

    //要求key至少长度为8个字符
    private static final String DES_KEY = "123456789";
    private String desEncodeString = null;

    @Test
    public void testDesEncrypt() {
        //加密
        byte[] encodeBytes = EncryptDecryptUtil.DesEncrypt(DES_KEY, "Hello, DES");
        desEncodeString = RadixConvertUtils.bytes2Hex(encodeBytes);
        log.info("des encrypt result: {}", desEncodeString);
        byte[] bytes1 = RadixConvertUtils.hex2Bytes(desEncodeString);
        log.info("encodeBytes: {}", encodeBytes);
        log.info("bytes1: {}", bytes1);
    }

    @Test
    public void testDesDecrypt() {
        if (desEncodeString == null) {
            testDesEncrypt();
        }
        byte[] desEncodeBytes = RadixConvertUtils.hex2Bytes(desEncodeString);
        byte[] decodeBytes = EncryptDecryptUtil.DesDecrypt(DES_KEY, desEncodeBytes);
        String strDecode = new String(decodeBytes);
        log.info("des decrypt result: {}", strDecode);
    }

    @Test
    public void testRsa() {
        // RSA
        // 数据使用私钥加密
        byte[] bytesSecret = EncryptDecryptUtil.RsaEncrypt("Hi, RSA");
        String strSecretData = Base64.getEncoder().encodeToString(bytesSecret);
        log.info("私钥加密密文: {}", strSecretData);

        // 用户使用公钥解密
        byte[] bytesDecrypt = EncryptDecryptUtil.RsaDecrypt(bytesSecret);
        String strData = new String(bytesDecrypt);
        log.info("使用公钥解密: {}", strData);

        // 服务器根据私钥和加密数据生成数字签名
        byte[] bytesSign = EncryptDecryptUtil.getSignature(bytesSecret);
        String strSign = Base64.getEncoder().encodeToString(bytesSign);
        log.info("数字签名: {}", strSign);

        // 用户根据公钥、加密数据验证数据是否被修改过
        boolean verify_result = EncryptDecryptUtil.verifySignature(bytesSecret, bytesSign);
        log.info("数据校验，是否原始数据性: {}", verify_result);
    }

    @Test
    public void testShaEncrypt() {
        //SHA
        String sha = EncryptDecryptUtil.ShaEncrypt("Hi, RSA");
        System.out.println(sha);
    }
}
