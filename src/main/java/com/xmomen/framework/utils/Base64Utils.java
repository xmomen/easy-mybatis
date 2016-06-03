package com.xmomen.framework.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by Jeng on 2015/6/19.
 */
public class Base64Utils {

    /**
     * base64加密(默认加密次数为1次)
     * @param str
     * @return
     */
    public static String encoder(String str){
        return encoder(str, 1);
    }

    /**
     * base64加密
     * @param str 加密字符
     * @param num 加密次数(num 小于等于 0 不加密)
     * @return
     */
    public static String encoder(String str,int num){
        int i = 0;
        String result = str;
        while (i < num){
            result = new BASE64Encoder().encode(result.getBytes());
            i++;
        }
        return result;
    }

    /**
     * base64解密
     * @param str 解密字符
     * @param num 解密次数
     * @return
     * @throws java.io.IOException
     */
    public static String decoder(String str,int num) throws IOException {
        int i = 0;
        String result = str;
        while (i < num){
            byte[] s = new BASE64Decoder().decodeBuffer(result);
            result = new String(s);
            i++;
        }
        return result;
    }

    /**
     * base64解密(默认解密次数为1次)
     * @param str 解密字符
     * @return
     * @throws java.io.IOException
     */
    public static String decoder(String str) throws IOException {
        return decoder(str, 1);
    }
}
