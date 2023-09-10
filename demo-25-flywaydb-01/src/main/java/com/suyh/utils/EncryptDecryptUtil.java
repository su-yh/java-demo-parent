package com.suyh.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 文件描述 加密解密工具类
 **/
@Slf4j
public class EncryptDecryptUtil {

    private static final String ALGORITHM_MD5 = "md5";
    private static final String ALGORITHM_DES = "des";
    private static final String ALGORITHM_RSA = "rsa";
    private static final String ALGORITHM_MD5_RSA = "MD5withRSA";
    private static final String ALGORITHM_SHA = "sha";
    private static final String ALGORITHM_MAC = "HmacMD5";

    private static final SecureRandom SECURE_RANDOM;
    private static MessageDigest MD5_UTIL;
    private static KeyPair keyPair; // 密钥对

    static {
        SECURE_RANDOM = new SecureRandom();
        try {
            MD5_UTIL = MessageDigest.getInstance(ALGORITHM_MD5);

            // 创建密钥对KeyPair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            // 密钥长度推荐为1024位
            keyPairGenerator.initialize(1024);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            log.error("EncryptDecryptUtil init failed.", e);
        }
    }

    /**
     * MD5简单加密
     *
     * @param content 加密内容
     * @return String
     */
    public static String md5Encrypt(final String content) {
        byte[] bytes = md5Encrypt(content.getBytes());
        return RadixConvertUtils.bytes2Hex(bytes);
    }

    /**
     * MD5简单加密
     *
     * @param data 待加密数据
     * @return 返回加密结果
     */
    public static byte[] md5Encrypt(final byte[] data) {
        return MD5_UTIL.digest(data);
    }

    /**
     * base64加密
     *
     * @param content 待加密内容
     * @return byte[]
     */
    public static byte[] base64Encrypt(final String content) {
        return Base64.getEncoder().encode(content.getBytes());
    }

    /**
     * base64解密
     *
     * @param encoderContent 已加密内容
     * @return byte[]
     */
    public static byte[] base64Decrypt(final byte[] encoderContent) {
        return Base64.getDecoder().decode(encoderContent);
    }

    /**
     * 根据key生成秘钥
     *
     * @param key 给定key,要求key至少长度为8个字符
     * @return SecretKey
     */
    public static SecretKey getSecretKey(final String key) {
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory instance = SecretKeyFactory.getInstance(ALGORITHM_DES);
            return instance.generateSecret(desKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * DES加密
     *
     * @param key     秘钥key
     * @param content 待加密内容
     * @return byte[]
     */
    public static byte[] DesEncrypt(final String key, final String content) {
        return processCipher(content.getBytes(), getSecretKey(key), Cipher.ENCRYPT_MODE, ALGORITHM_DES);
    }

    /**
     * DES解密
     *
     * @param key            秘钥key
     * @param encoderContent 已加密内容
     * @return byte[]
     */
    public static byte[] DesDecrypt(final String key, final byte[] encoderContent) {
        return processCipher(encoderContent, getSecretKey(key), Cipher.DECRYPT_MODE, ALGORITHM_DES);
    }

    /**
     * 加密/解密处理
     *
     * @param processData 待处理的数据
     * @param key         提供的密钥
     * @param opsMode     工作模式
     * @param algorithm   使用的算法
     * @return byte[]
     */
    private static byte[] processCipher(
            final byte[] processData, final Key key,
            final int opsMode, final String algorithm) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(opsMode, key, SECURE_RANDOM);
            return cipher.doFinal(processData);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException, processCipher", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException, processCipher", e);
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException, processCipher", e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException, processCipher", e);
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException, processCipher", e);
        }

        return null;
    }

    /**
     * 获取私钥，用于RSA非对称加密.
     *
     * @return PrivateKey
     */
    public static PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    /**
     * 获取公钥，用于RSA非对称加密.
     *
     * @return PublicKey
     */
    public static PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    /**
     * 获取数字签名，用于RSA非对称加密.
     *
     * @return byte[]
     */
    public static byte[] getSignature(final byte[] encoderContent) {
        try {
            Signature signature = Signature.getInstance(ALGORITHM_MD5_RSA);
            signature.initSign(keyPair.getPrivate());
            signature.update(encoderContent);
            return signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证数字签名，用于RSA非对称加密.
     *
     * @return byte[]
     */
    public static boolean verifySignature(final byte[] encoderContent, final byte[] signContent) {

        try {
            Signature signature = Signature.getInstance(ALGORITHM_MD5_RSA);
            signature.initVerify(keyPair.getPublic());
            signature.update(encoderContent);
            return signature.verify(signContent);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException, verifySignature failed.", e);
        } catch (SignatureException e) {
            log.error("SignatureException, verifySignature failed.", e);
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException, verifySignature failed.", e);
        }

        return false;
    }

    /**
     * RSA加密
     *
     * @param content 待加密内容
     * @return byte[]
     */
    public static byte[] RsaEncrypt(final String content) {
        return processCipher(content.getBytes(), keyPair.getPrivate(), Cipher.ENCRYPT_MODE, ALGORITHM_RSA);
    }

    /**
     * RSA解密
     *
     * @param encoderContent 已加密内容
     * @return byte[]
     */
    public static byte[] RsaDecrypt(final byte[] encoderContent) {
        return processCipher(encoderContent, keyPair.getPublic(), Cipher.DECRYPT_MODE, ALGORITHM_RSA);
    }

    /**
     * SHA加密
     *
     * @param content 待加密内容
     * @return String
     */
    public static String ShaEncrypt(final String content) {
        try {
            MessageDigest sha = MessageDigest.getInstance(ALGORITHM_SHA);
            byte[] sha_byte = sha.digest(content.getBytes());
            return RadixConvertUtils.bytes2Hex(sha_byte);
//            StringBuffer hexValue2 = new StringBuffer();
//            for (int i = 0; i < sha_byte.length; i++) {
//                int val = ((int) sha_byte[i]) & 0xff;
//                if (val < 16) {
//                    hexValue2.append("0");
//                }
//                hexValue2.append(Integer.toHexString(val));
//            }
//            return hexValue2.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException SHAEncrypt failed", e);
        }
        return null;
    }

    /**
     * HMAC加密
     *
     * @param key     给定秘钥key
     * @param content 待加密内容
     * @return String
     */
    public static byte[] HMACEncrypt(final String key, final String content) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM_MAC);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            //初始化mac
            mac.init(secretKey);
            return mac.doFinal(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
