package net.blissmall.puff.common.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

import static com.google.zxing.client.j2se.MatrixToImageWriter.writeToPath;


/**
 * 二维码 / 条形码  编码和解码
 *
 * @author zhuzhenglin
 * @Email zhenglin.zhu@xfxb.net
 */
public class ZxingUtils {

    private final static String DEFUALT_IMAGE_FORMAT = "PNG";

    /**
     * 条形码编码
     *
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public static void encode(String contents, int width, int height, String imgPath) {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_13, codeWidth, height, null);

            writeToPath(bitMatrix, "png", new File(imgPath).toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 条形码解码
     *
     * @param imgPath
     * @return String
     */
    public static String decode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二维码编码
     *
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public static void encode2(String contents, int width, int height, String imgPath) {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, width, height, hints);

            MatrixToImageWriter.writeToPath(bitMatrix, DEFUALT_IMAGE_FORMAT, new File(imgPath).toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接将生成的二维码以流的形式输出
     * @param contents
     * @param width
     * @param height
     * @param os
     */
    public static void encodeToStream(String contents, int width, int height, OutputStream os){
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix,DEFUALT_IMAGE_FORMAT,os);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 二维码解码
     *
     * @param imgPath
     * @return String
     */
    public static String decode2(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
            hints.put(DecodeHintType.CHARACTER_SET, "GBK");

            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        // 条形码
        String imgPath = "target\\zxing_EAN13.png";
        String contents = "6923450657713";
        int width = 105, height = 50;

        ZxingUtils.encode(contents, width, height, imgPath);
        System.out.println("finished zxing EAN-13 encode.");

        String decodeContent = ZxingUtils.decode(imgPath);
        System.out.println("解码内容如下：" + decodeContent);
        System.out.println("finished zxing EAN-13 decode.");

        // 二维码
        String imgPath2 = "target\\zxing.png";
        String contents2 = "Hello Gem, welcome to Zxing!"
                + "\nBlog [ http://thinkgem.iteye.com ]"
                + "\nEMail [ thinkgem@163.com ]";
        int width2 = 300, height2 = 300;

        ZxingUtils.encode2(contents2, width2, height2, imgPath2);
        System.out.println("finished zxing encode.");

        String decodeContent2 = ZxingUtils.decode2(imgPath2);
        System.out.println("解码内容如下：" + decodeContent2);
        System.out.println("finished zxing decode.");

    }

}