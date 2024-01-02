
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

@Slf4j
public class Sha256Demo {

    public static void main(String[] args) {
        log.info("SHA256(1)=" + getSha256Txt("1"));
        log.info("SHA256(2)=" + getSha256Txt("2"));
        log.info("SHA256(3)=" + getSha256Txt("!@#$%^&*()_+1234567890abcdefghijklmnopqrstuvwxyz================================================================================================="));
    }

    private static String getSha256Txt(String str) {
        MessageDigest messageDigest;

        String encodeTxt = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));

            encodeTxt = encodeHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException exception) {
            log.error("exception.", exception);
        }

        return encodeTxt;
    }

    public static String encodeHexString(byte[] data) {

        StringBuilder builder = new StringBuilder();

        for (byte dataValue : data) {

            builder.append(String.format("%02x", dataValue));

        }

        return builder.toString();
    }

    public static String encodeHexString2(byte[] data) {

        Formatter formatter = new Formatter();

        for (byte dataValue : data) {

            formatter.format("%02x", dataValue);

        }

        String result = formatter.toString();

        formatter.close();

        return result;

    }

    private static String byteToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();

        String temp;

        for (byte byValue : bytes) {
            temp = Integer.toHexString(byValue & 0xFF);

            // 得到一位的进行补0操作
            if (temp.length() == 1) {
                builder.append(0);
            }

            builder.append(temp);
        }

        return builder.toString();
    }
}
