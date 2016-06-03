package com.xmomen.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * MD5工具类
 */
public class MD5Utils extends BaseUtils {

    private static final Logger log = LoggerFactory.getLogger(MD5Utils.class);

    private static final String[] SALEARR = {"q", "a", "z", "w", "s", "x", "e", "d", "c", "r", "f", "v", "t", "g", "b", "y", "h", "n", "u", "j", "m", "i", "k", "o", "l", "p", "_"};

    static char[] chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'h',
            'i', 'o', 't', 'y', 'E', 'D', 'O', 'T', 'Y',
            'j', 'p', 'u', 'z', 'F', 'J', 'P', 'U', 'Z',
            'k', 'q', 'v', 'A', 'G', 'K', 'Q', 'V',
            'l', 'r', 'w', 'B', 'H', 'L', 'R', 'W',
            'm', 's', 'x', 'C', 'I', 'M', 'S', 'X'};

    /**
     * 生成盐值
     *
     * @return
     */
    public static String getSalt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            sb.append(SALEARR[random.nextInt(26)]);
        }
        return sb.toString();
    }

    /**
     * 按加密次数加密
     * @param str        加密字符串
     * @param salt       加密盐值
     * @param encryptNum 加密次数：小于等于0则不加密
     * @return
     */
    public static String encrypt(String str, String salt, int encryptNum){
        int i = 0;
        String result = str;
        while (i < encryptNum){
            result = encrypt(result, salt);
            i++;
        }
        return result;
    }

    /**
     * MD5加密
     *
     * @param str
     * @param salt
     * @return
     */
    public static String encrypt(String str, String salt) {
        MessageDigest messageDigest = null;
        str += salt;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(),e);
            return null;
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
            return null;
        }
        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            String hexString = Integer.toHexString(0xFF & byteArray[i]);
            if (hexString.length() == 1){
                md5StrBuff.append("0").append(hexString);
            }else{
                md5StrBuff.append(hexString);
            }
        }
        return md5StrBuff.toString();
    }

}
