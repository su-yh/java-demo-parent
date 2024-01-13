package com.suyh;

import com.suyh.encrypt.PropertiesEncrypt;
import lombok.extern.slf4j.Slf4j;

/**
 * @author suyh
 * @since 2024-01-13
 */
@Slf4j
public class EncryptMain {
    public static void main(String[] args) {
        PropertiesEncrypt encrypt = new PropertiesEncrypt();
        {
            String password = "root";
            String strEncrypt = encrypt.encrypt(password);
            log.info("'{}' encrypt result: {}", password, strEncrypt);
        }

        {
            String password = "suyh-personal";
            String strEncrypt = encrypt.encrypt(password);
            log.info("'{}' encrypt result: {}", password, strEncrypt);
        }
    }
}
