package com.suyh.encrypt;

import com.suyh.common.Constants;
import com.suyh.utils.EncryptDecryptUtil;
import com.suyh.utils.RadixConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

/**
 * 需要实现这个接口
 * 并处理成一个bean 对象
 */
@Component("propertiesEncrypt")
@Slf4j
public class PropertiesEncrypt implements StringEncryptor {
    @Override
    public String encrypt(String value) {
        log.info("PropertiesEncrypt, encrypt: {}", value);
        byte[] encodeBytes = EncryptDecryptUtil.DesEncrypt(Constants.DES_SECRET_KEY, value);
        return RadixConvertUtils.bytes2Hex(encodeBytes);
    }

    @Override
    public String decrypt(String value) {
        byte[] desEncodeBytes = RadixConvertUtils.hex2Bytes(value);
        byte[] decodeBytes = EncryptDecryptUtil.DesDecrypt(Constants.DES_SECRET_KEY, desEncodeBytes);
        String strDecode = new String(decodeBytes);
        log.info("des decrypt, value: {}, result: {}", value, strDecode);
        return strDecode;
    }
}
