package com.suyh.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 进制转换工具类
 */
@Slf4j
public class RadixConvertUtils {
    /**
     * hex转byte数组 无前缀
     *
     * @param hex 十六进制字符串
     * @return
     */
    public static byte[] hex2Bytes(String hex) {
        if (hex.length() % 2 != 0) {
            log.error("hexToByte failed, length: {}", hex.length());
            return null;
        }
        int byteSize = hex.length() / 2;
        byte[] ret = new byte[byteSize];
        for (int i = 0; i < byteSize; i++) {
            int beginIndex = i * 2;
            String hexStr = hex.substring(beginIndex, beginIndex + 2);
            ret[i] = (byte) Integer.parseInt(hexStr, 16);
        }

        return ret;
    }

    /**
     * byte数组转hex
     *
     * @param bytes 字节数据
     * @return
     */
    public static String bytes2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String strHex = Integer.toHexString(aByte & 0xFF);
            if (strHex.length() == 1) {
                // 每个字节由两个字符表示，位数不够，高位补0
                sb.append("0");
            }
            sb.append(strHex);
        }
        return sb.toString();
    }
}
