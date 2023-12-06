package com.suyh6004;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

/**
 * @author suyh
 * @since 2023-12-06
 */
public class DemoGoogleTotpApplication {
    public static void main(String[] args) {
        {
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            final GoogleAuthenticatorKey key = gAuth.createCredentials();

            String key1 = key.getKey();
            System.out.println("key1: " + key1);
        }

        {
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
//            boolean isCodeValid = gAuth.authorize(secretKey, password);
        }

        {
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            int code = gAuth.getTotpPassword("56F7IKTPEKOMRBMOCX4HPV52AFSTWZHX");
            System.out.println("code: " + code);
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
