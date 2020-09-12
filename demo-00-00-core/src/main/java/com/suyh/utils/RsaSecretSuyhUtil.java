package com.suyh.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
public class RsaSecretSuyhUtil {
    private RsaSecretSuyhUtil() {
    }

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


    public static KeyPair makeRsaSecretKeySuyh() {

        try {
            // 创建密钥对KeyPair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            // 密钥长度推荐为1024位
            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGenerator.initialize(1024);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException, makeRsaSecretKeySuyh failed.", e);
        }

        return null;
    }

    /**
     * 解码PublicKey
     *
     * @param key
     * @return
     */
    public static PublicKey getPublicKeySuyh(String key) {
        try {
            byte[] byteKey = RadixConvertUtils.hex2Bytes(key);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException, getPublicKeySuyh failed", e);
        } catch (InvalidKeySpecException e) {
            log.error("InvalidKeySpecException, getPublicKeySuyh failed", e);
        }
        return null;
    }

    /**
     * 解码PrivateKey
     *
     * @param key
     * @return
     */
    public static PrivateKey getPrivateKeySuyh(String key) {
        try {
            // byte[] byteKey = Base64.getDecoder().decode(key);
            byte[] byteKey = RadixConvertUtils.hex2Bytes(key);
            PKCS8EncodedKeySpec x509EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(x509EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException, getPrivateKeySuyh failed.", e);
        } catch (InvalidKeySpecException e) {
            log.error("InvalidKeySpecException, getPrivateKeySuyh failed.", e);
        }
        return null;
    }

    // 使用私钥加密
    public static byte[] RsaEncryptByPrivateKeySuyh(final String content, final String strPrivateKey) {
        PrivateKey privateKey = getPrivateKeySuyh(strPrivateKey);

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey, SECURE_RANDOM);
            return cipher.doFinal(content.getBytes());
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

    // 使用公钥解密
    public static byte[] RsaDecryptByPublicKeySuyh(
            final byte[] encoderContent, final String strPublicKey) {
        PublicKey publicKey = getPublicKeySuyh(strPublicKey);

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, publicKey, SECURE_RANDOM);
            return cipher.doFinal(encoderContent);
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

    // 使用私钥加密
    public static byte[] RsaEncryptByPublicKeySuyh(final String content, final String strPublicKey) {
        PublicKey publicKeySuyh = getPublicKeySuyh(strPublicKey);

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKeySuyh, SECURE_RANDOM);
            return cipher.doFinal(content.getBytes());
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

    // 使用公钥解密
    public static byte[] RsaDecryptByPrivateKeySuyh(final byte[] encoderContent, final String strPrivateKey) {
        PrivateKey privateKey = getPrivateKeySuyh(strPrivateKey);

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey, SECURE_RANDOM);
            return cipher.doFinal(encoderContent);
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

}
