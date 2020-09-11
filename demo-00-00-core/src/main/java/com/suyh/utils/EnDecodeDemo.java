package com.suyh.utils;



import java.util.Base64;

/**
 * 文件描述 加密解密方式
 **/
public class EnDecodeDemo {

    public static void main(String[] args) {
        //md5简单加密
        String text = "i am text";
        System.out.println(EncryptDecryptUtil.md5Encrypt(text));

        //base64进行加密解密,通常用作对二进制数据进行加密
        byte[] base64Encrypt = EncryptDecryptUtil.base64Encrypt("123456789");
//        String toHexString = HexUtils.toHexString(base64Encrypt);
//        System.out.println(toHexString);
        byte[] base64Decrypt = EncryptDecryptUtil.base64Decrypt(base64Encrypt);
        System.out.println(new String(base64Decrypt));

        //DES对称加密/解密
        //要求key至少长度为8个字符
        String key = "123456789";
        //加密
        byte[] encode_bytes = EncryptDecryptUtil.DesEncrypt(key, "Hello, DES");
        System.out.println(Base64.getEncoder().encodeToString(encode_bytes));
        //解密
        byte[] decode_bytes = EncryptDecryptUtil.DesDecrypt(key, encode_bytes);
        System.out.println(new String(decode_bytes));

        //RSA
        //数据使用私钥加密
        byte[] en_byte = EncryptDecryptUtil.RsaEncrypt("Hi, RSA");
        System.out.println(Base64.getEncoder().encodeToString(en_byte));

        //用户使用公钥解密
        byte[] de_byte = EncryptDecryptUtil.RsaDecrypt(en_byte);
        System.out.println(new String(de_byte));

        //服务器根据私钥和加密数据生成数字签名
        byte[] sign_byte = EncryptDecryptUtil.getSignature(en_byte);
        System.out.println(Base64.getEncoder().encodeToString(sign_byte));

        //用户根据公钥、加密数据验证数据是否被修改过
        boolean verify_result = EncryptDecryptUtil.verifySignature(en_byte, sign_byte);
        System.out.println(verify_result);

        //SHA
        String sha = EncryptDecryptUtil.ShaEncrypt("Hi, RSA");
        System.out.println(sha);

        //HMAC
        byte[] mac_bytes = EncryptDecryptUtil.HMACEncrypt(key, "Hi, HMAC");
//        System.out.println(HexUtils.toHexString(mac_bytes));
    }
}
