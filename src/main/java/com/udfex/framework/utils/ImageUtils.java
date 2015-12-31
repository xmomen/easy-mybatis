package com.udfex.framework.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

import javax.imageio.ImageIO;

/**
 * 图片压缩工具
 */
public class ImageUtils extends BaseUtils {

    private static final String DEFAULT_FORMAT = "JPG";

    private static final double DEFAULT_SCALE = 1;

    public static InputStream scaleImage(File file) throws IOException {
        return scaleImage(file, DEFAULT_SCALE, DEFAULT_FORMAT);
    }

    public static InputStream scaleImage(File file, double scale) throws IOException {
        return scaleImage(file, scale, DEFAULT_FORMAT);
    }

    public static InputStream scaleImage(File file, double scale, String format) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        width = parseDoubleToInt(width * scale);
        height = parseDoubleToInt(height * scale);
        return scaleImage(bufferedImage, width, height, false, format);
    }

    public static InputStream scaleImage(InputStream inputStream) throws IOException {
        return scaleImage(inputStream, DEFAULT_SCALE ,DEFAULT_FORMAT);
    }

    public static InputStream scaleImage(InputStream inputStream, double scale) throws IOException {
        return scaleImage(inputStream, scale ,DEFAULT_FORMAT);
    }

    public static InputStream scaleImage(InputStream inputStream, double scale, String format) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(copyInputStream(inputStream));
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        width = parseDoubleToInt(width * scale);
        height = parseDoubleToInt(height * scale);
        return scaleImage(bufferedImage, width, height, false, format);
    }

    public static InputStream scaleImage(InputStream inputStream, int width, int height) throws IOException {
        return scaleImage(inputStream, width, height, DEFAULT_FORMAT);
    }

    public static InputStream scaleImage(InputStream inputStream, int width, int height, String format) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(copyInputStream(inputStream));
        return scaleImage(bufferedImage, width, height, false, format);
    }

    /**
     * 压缩基础方法
     * @param bufferedImage
     * @param width
     * @param height
     * @param auto 是否自动保持图片的原高宽比例
     * @param format 图片格式
     * @return
     * @throws java.io.IOException
     */
    private static InputStream scaleImage(BufferedImage bufferedImage, int width, int height, boolean auto, String format) throws IOException {
        // 临时文件
        File tempFile = File.createTempFile(UUID.randomUUID().toString(), DEFAULT_FORMAT);
        if (auto) {
            ArrayList<Integer> paramsArrayList = getAutoWidthAndHeight(bufferedImage, width, height);
            width = paramsArrayList.get(0);
            height = paramsArrayList.get(1);
            log.debug(MessageFormat.format("自动调整比例，width={0} height={1}", width, height));
        }
        Image image = bufferedImage.getScaledInstance(width, height,
                Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = outputImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        ImageIO.write(outputImage, DEFAULT_FORMAT, tempFile);
        InputStream inputStream1 = new FileInputStream(tempFile);
        tempFile.delete();
        return inputStream1;
    }

    private static InputStream copyInputStream(InputStream inputStream) throws IOException {
        byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(inputStream);
        return new ByteArrayInputStream(byteArray);
    }

    /**
     * 将double类型的数据转换为int，四舍五入原则
     *
     * @param sourceDouble
     * @return
     */
    private static int parseDoubleToInt(double sourceDouble) {
        int result = 0;
        result = (int) sourceDouble;
        return result;
    }

    /**
     * @param bufferedImage 要缩放的图片对象
     * @param width_scale   要缩放到的宽度
     * @param height_scale  要缩放到的高度
     * @return 一个集合，第一个元素为宽度，第二个元素为高度
     */
    private static ArrayList<Integer> getAutoWidthAndHeight(BufferedImage bufferedImage, int width_scale, int height_scale) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        double scale_w = getDot2Decimal(width_scale, width);
        double scale_h = getDot2Decimal(height_scale, height);
        if (scale_w < scale_h) {
            arrayList.add(parseDoubleToInt(scale_w * width));
            arrayList.add(parseDoubleToInt(scale_w * height));
        } else {
            arrayList.add(parseDoubleToInt(scale_h * width));
            arrayList.add(parseDoubleToInt(scale_h * height));
        }
        return arrayList;

    }


    /**
     * 返回两个数a/b的小数点后三位的表示
     *
     * @param a
     * @param b
     * @return
     */
    public static double getDot2Decimal(int a, int b) {
        BigDecimal bigDecimal_1 = new BigDecimal(a);
        BigDecimal bigDecimal_2 = new BigDecimal(b);
        BigDecimal bigDecimal_result = bigDecimal_1.divide(bigDecimal_2, new MathContext(4));
        Double double1 = new Double(bigDecimal_result.toString());
        System.out.println("相除后的double为：" + double1);
        return double1;
    }

}

