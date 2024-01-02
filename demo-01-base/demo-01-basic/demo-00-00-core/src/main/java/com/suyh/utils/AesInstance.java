package com.suyh.utils;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AesInstance {
    private Key secretKey;

    //  初始向量IV, 初始向量IV的长度规定为128位16个字节, 初始向量的来源为随机生成.
    private static final byte[] KEY_VI = "c558Gq0YQK2QUlMc".getBytes();

    // 注意这个随机种，加解密需要一致。
    public boolean init(byte[] keySeed) {
        if (keySeed == null || keySeed.length == 0) {
            return false;
        }
        secretKey = generateKey(keySeed);
        if (secretKey == null) {
            return false;
        }
        return true;
    }

    private Key generateKey(byte[] keySeed) {
        try {
            // 这里应该要注意一下，这个随机种子，加解密的随机种子应该是需要一致才行，否则解密会有问题。
            // 所以如果在丙服务之间处理时，应该是个常量。
            final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed);
            final KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] encrypt(byte[] plainValue) {
        try {
            // AES 加密分ECB 和CBC 两种模式
            // final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(KEY_VI));
            return cipher.doFinal(plainValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] decrypt(byte[] cipherValue) {
        try {
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(KEY_VI));
            return cipher.doFinal(cipherValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        test1();
         test2();
    }


    private static void test2() {
        AesInstance aesInstance = new AesInstance();
        if (!aesInstance.init("fendo8881".getBytes(StandardCharsets.UTF_8))) {
            System.out.println("init failed.");
            return;
        }

        String base64CipherValue = "gID0hYTWcc42tHxrQjS2RCREqBOEDyepaZQdiuCTr7U=";
        final byte[] cipherValue = Base64Utils.decodeFromString(base64CipherValue);
        final byte[] plainValue = aesInstance.decrypt(cipherValue);
        String plainText = new String(plainValue, StandardCharsets.UTF_8);
        System.out.println("plainText: " + plainText);
    }

    private static void test1() {
        AesInstance aesInstance = new AesInstance();
        if (!aesInstance.init("fendo8881".getBytes(StandardCharsets.UTF_8))) {
            System.out.println("init failed.");
            return;
        }

        for (int i = 0; i < 4; i++) {
            byte[] srcValue = "nothing-plain-text".getBytes(StandardCharsets.UTF_8);
            final byte[] cipherValue = aesInstance.encrypt(srcValue);

            final byte[] plainValue = aesInstance.decrypt(cipherValue);
            String plainText = new String(plainValue, StandardCharsets.UTF_8);
            System.out.println("plainText: " + plainText);
            final String base64Value = Base64Utils.encodeToString(cipherValue);
            System.out.println("base64 cipher value: " + base64Value);
        }
    }
}
