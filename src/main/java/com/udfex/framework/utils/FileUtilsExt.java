package com.udfex.framework.utils;

import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Created by Jeng on 2015/4/16.
 */
public class FileUtilsExt extends FileUtils {

    /**
     * base64字符串解密为输入流
     * @param base64String
     * @return
     */
    public static InputStream BASE64Decoder2InputStream(String base64String){
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decoderBytes = new byte[0];
        try {
            decoderBytes = decoder.decodeBuffer(base64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(decoderBytes);
    }

    /**
     * 输入流加密为base64字符串
     * @param inputStream
     * @return
     */
    public static String BASE64Encoder4InputStream(InputStream inputStream){
        BASE64Encoder encoder = new BASE64Encoder();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream input = inputStream;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            for(int len = input.read(temp); len != -1;len = input.read(temp)){
                out.write(temp, 0, len);
                stringBuilder.append(encoder.encode(out.toByteArray()));
                out.reset();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 文件加密为base64字符串
     * @param file
     * @return
     */
    public static String BASE64Encoder4File(File file){
        BASE64Encoder encoder = new BASE64Encoder();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream input = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            for(int len = input.read(temp); len != -1;len = input.read(temp)){
                out.write(temp, 0, len);
                stringBuilder.append(encoder.encode(out.toByteArray()));
                out.reset();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
