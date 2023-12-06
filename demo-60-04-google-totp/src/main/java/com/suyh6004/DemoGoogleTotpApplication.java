package com.suyh6004;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author suyh
 * @since 2023-12-06
 */
public class DemoGoogleTotpApplication {
    private static final String secretKey = "56F7IKTPEKOMRBMOCX4HPV52AFSTWZHX";
    public static void main(String[] args) throws InterruptedException, IOException, WriterException {
        {
            if (false) {
                // 二维码生成，这个google 提供的，似乎只有google 的二次认证可以识别，我使用 privacyidea.xx 手机app 识别不了。
                String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthURL("issuer", "accountName", new GoogleAuthenticator().createCredentials());
                System.out.println("otpAuthUrl: " + otpAuthURL);

                return;
            }
        }
        {
            if (false) {
                // 生成二维码数据
                String qrData = spawnScanQrString("suyh", secretKey, "aiteer-vcs");
                System.out.println("qrData: " + qrData);

                // 这个二维码数据的图片，返回给前端
                byte[] qrCodeValue = createQrCode(qrData, "a.png", 200, 200);
                return;
            }
        }

        if (false) {
            return;
        }

        {
            if (false) {
                // google 提供的一个key 的随机生成器，这个key 没有多大关系，可以自己生成。
                // 只不过既然google 提供了，那就用一下。
                GoogleAuthenticator gAuth = new GoogleAuthenticator();
                final GoogleAuthenticatorKey googleAuthenticatorKey = gAuth.createCredentials();

                // 将这个key 存储起来，并提供给客户端。或者说提供给其他端，那么最后在同一个时间段(这个似乎是60 秒呀)内。
                // 使用相同的算法，最终得到的6 位数字将会是相同的。
                String strKey = googleAuthenticatorKey.getKey();
                System.out.println("strKey: " + strKey);
                return;
            }
        }


        int code = 0;
        {
            // 这里是google 提供的生成这个code 的方法，一般用在手机端。
            // 得到了该值之后，在这段时间内它在其他地方用相同的key 可以得到相同的code 值。
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            code = gAuth.getTotpPassword(secretKey);
            System.out.println("code: " + code);
        }

        {
            // 校验外部传入的code 与当前系统中生成的code 是否一致。
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            boolean isCodeValid = gAuth.authorize(secretKey, code);
            System.out.println("result: " + isCodeValid);

            if (true) {
                return;
            }
        }

        // 60 秒之后就校验不过了，因为这个code 的生成规则 是通过时间范围来确定的。
        TimeUnit.SECONDS.sleep(60);

        {
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            boolean isCodeValid = gAuth.authorize(secretKey, code);
            System.out.println("sleep 60 seconds, result: " + isCodeValid);
        }
    }

    public static String spawnScanQrString(String account, String secretKey, String title) {
        try {
            return "otpauth://totp/"
                    + URLEncoder.encode(title + ":" + account, StandardCharsets.UTF_8.name()).replace("+", "%20")
                    + "?secret=" + URLEncoder.encode(secretKey, StandardCharsets.UTF_8.name()).replace("+", "%20")
                    + "&issuer=" + URLEncoder.encode(title, StandardCharsets.UTF_8.name()).replace("+", "%20")
                    ;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // 方式一
    /**
     * 将这个二维码数据，生成一个图片信息数据。
     *
     * @param barCodeData  二维码数据
     * @param outPath  如果指定了路径，则将二维码图片写到该文件中
     * @param height 二维码的高
     * @param width 二维码的宽
     * @return 图片的二进制数据
     * @throws WriterException
     * @throws IOException
     */
    public static byte[] createQrCode(String barCodeData, String outPath, int height, int width) throws WriterException, IOException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix matrix = multiFormatWriter.encode(barCodeData, BarcodeFormat.QR_CODE, width, height);

        if (outPath != null && !outPath.isEmpty()) {
            // 如果指定了路径，则写到对应的路径文件中。
            try (FileOutputStream out = new FileOutputStream(outPath)) {
                MatrixToImageWriter.writeToStream(matrix, "png", out);
            }
        }

        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);

        ByteArrayOutputStream bof = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", bof);

        return bof.toByteArray();
    }

    private static String imageToBase64(byte[] dataBytes) {
        if (dataBytes == null) {
            return null;
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return "data:image/jpeg;base64," + encoder.encode(dataBytes);
    }

    private static final int FRONT_COLOR = 0x000000;
    private static final int BACKGROUND_COLOR = 0xFFFFFF;

    // 方式二
    private void createQrCode(String barCodeData, OutputStream outputStream, int height, int width) throws WriterException, IOException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(barCodeData, BarcodeFormat.QR_CODE, width, height);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
            }
        }

        ImageIO.write(bufferedImage, "png", outputStream);
    }
}
