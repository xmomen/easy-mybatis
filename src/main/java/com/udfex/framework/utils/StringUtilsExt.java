package com.udfex.framework.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Jeng on 2015/4/16.
 */
public class StringUtilsExt extends StringUtils {

    /**
     * 生成32位UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replaceAll("-", "");
    }

    /**
     * 生成指定位数UUID
     * @param length
     * @return
     */
    public static String getUUID(int length) {
        Assert.isTrue(length > 0 && length <= 32, "length must be > 0 and <= 32");
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replaceAll("-", "");
        return str.substring(0,length);
    }

    /**
     * 生成指定位数的数字
     * @param len
     * @return
     */
    public static String random(int len) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            sb.append(new Random().nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 汉字转拼音
     * @param str
     * @return
     */
    public static String converToPinyin(String str) {
        String result = "";
        try {
            if(str != null && isNotBlank(str)) {
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
                defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
                defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
                char[] c = str.toCharArray();
                int l = c.length;
                for(int i = 0; i < l; i ++) {
                    if(Character.toString(c[i]).matches("[\\u4E00-\\u9FA5]+")) {
                        String s = PinyinHelper.toHanyuPinyinStringArray(c[i], defaultFormat)[0];
                        s = s.substring(0, 1).toUpperCase() + s.substring(1);
                        result += s;
                    } else {
                        result += c[i];
                    }
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return result;
    }
}
