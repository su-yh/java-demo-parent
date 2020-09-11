package com.suyh.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HexUtils {
    /**
     * hex转byte数组 无前缀
     *
     * @param hex 十六进制字符串
     * @return
     */
    public static byte[] hexToByte(String hex) {
        if (hex.length() % 2 != 0) {
            log.error("hexToByte failed, length: {}", hex.length());
            return null;
        }
        int byteSize = hex.length() / 2;
        byte[] ret = new byte[byteSize];
        for (int i = 0, j = 0; i < byteSize; i++) {
            int beginIndex = i * 2;
            String hexStr = hex.substring(beginIndex, beginIndex + 2);
            ret[j] = (byte) Integer.parseInt(hexStr, 16);
        }

        return ret;
    }

    /**
     * byte数组转hex
     *
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes) {
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
