package com.suyh6004;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import java.util.concurrent.TimeUnit;

/**
 * @author suyh
 * @since 2023-12-06
 */
public class DemoGoogleTotpApplication {
    public static void main(String[] args) throws InterruptedException {
        {
            // google 提供的一个key 的随机生成器，这个key 没有多大关系，可以自己生成。
            // 只不过既然google 提供了，那就用一下。
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            final GoogleAuthenticatorKey googleAuthenticatorKey = gAuth.createCredentials();

            // 将这个key 存储起来，并提供给客户端。或者说提供给其他端，那么最后在同一个时间段(这个似乎是60 秒呀)内。
            // 使用相同的算法，最终得到的6 位数字将会是相同的。
            String strKey = googleAuthenticatorKey.getKey();
            System.out.println("strKey: " + strKey);
        }

        String secretKey = "56F7IKTPEKOMRBMOCX4HPV52AFSTWZHX";
        int code = 0;
        {
            // 这里是google 提供的生成这个code 的方法，一般用在手机端。
            // 得到了该值之后，在这段时间内它在其他地方用相同的key 可以得到相同的code 值。
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            code = gAuth.getTotpPassword(secretKey);
            System.out.println("code: " + code);
        }

        {
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            boolean isCodeValid = gAuth.authorize(secretKey, code);
            System.out.println("result: " + isCodeValid);
        }

        TimeUnit.SECONDS.sleep(60);

        {
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            boolean isCodeValid = gAuth.authorize(secretKey, code);
            System.out.println("sleep 60 seconds, result: " + isCodeValid);
        }

//        googleauth
//        String secret = GoogleAuthenticator.generateSecretKey();
//        String qrcode = GoogleAuthenticator.getQRBarcodeURL("Java技术栈", "javastack.cn", secret);
//        System.out.println("二维码地址:" + qrcode);
//        System.out.println("密钥:" + secret);
    }

//    private void function(String barCodeData, String outPath, int height, int width) {
//        BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE, width, height);
//
//    }
}
