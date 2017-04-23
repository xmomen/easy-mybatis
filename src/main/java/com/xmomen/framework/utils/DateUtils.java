package com.xmomen.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Date utility methods.
 *
 * @author jeng
 */
@Deprecated
public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");

    public static Date parse(final String source) throws ParseException {
        if (source == null || source.trim().length() == 0) {
            return null;
        }
        if (source.length() <= dateTimeFormat.toPattern().length()
                && source.length() >= dateTimeFormat.toPattern().length() - 5) {
            try {
                return dateTimeFormat.parse(source);
            } catch (ParseException ex) {
            }
        }
        if (source.length() <= dateFormat.toPattern().length()
                && source.length() >= dateFormat.toPattern().length() - 2) {
            try {
                return dateFormat.parse(source);
            } catch (ParseException ex) {
            }
        }
        if (source.length() <= monthFormat.toPattern().length()
                && source.length() >= monthFormat.toPattern().length() - 1) {
            try {
                return monthFormat.parse(source);
            } catch (ParseException ex) {
            }
        }
        return dateTimeFormat.parse(source);
    }

    /**
     * Parse the Date using pattern "yyyy-MM-dd"
     *
     * @param source
     * @return
     * @throws java.text.ParseException
     */
    public static Date parseDate(final String source) throws ParseException {
        if (source == null || source.trim().length() == 0) {
            return null;
        }
        return dateFormat.parse(source);
    }

    /**
     * Format the Date using pattern "yyyy-MM-dd"
     *
     * @param date
     * @return
     */
    public static String formatDate(final Date date) {
        return formatDate(date, null);
    }

    /**
     * Format the Date using pattern "yyyyMMdd"
     *
     * @return
     */
    public static String getDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }

    /**
     * Format the Date using pattern "yyyyMMddHHmmssSSS"
     * @return
     */
    public static String getDateTimeString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date());
    }

    public static String formatDate(final Date date, final String defaultValue) {
        if (date == null) {
            return defaultValue;
        }
        return dateFormat.format(date);
    }

    /**
     * Parse the Date using pattern "yyyy-MM-dd HH:mm:ss"
     *
     * @param source
     * @return
     * @throws java.text.ParseException
     */
    public static Date parseDateTime(final String source) throws ParseException {
        if (source == null || source.trim().length() == 0) {
            return null;
        }
        return dateTimeFormat.parse(source);
    }

    /**
     * Format the Date using pattern "yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String formatDateTime(final Date date) {
        return formatDateTime(date, null);
    }

    public static String formatDateTime(final Date date, final String defaultValue) {
        if (date == null) {
            return defaultValue;
        }
        return dateTimeFormat.format(date);
    }

    /**
     * Parse the Date using pattern "yyyy-MM"
     *
     * @param source
     * @return
     * @throws java.text.ParseException
     */
    public static Date parseMonth(final String source) throws ParseException {
        if (source == null || source.trim().length() == 0) {
            return null;
        }
        return monthFormat.parse(source);
    }

    /**
     * Format the Date using pattern "yyyy-MM"
     *
     * @param date
     * @return
     */
    public static String formatMonth(final Date date) {
        return formatMonth(date, null);
    }

    public static String formatMonth(final Date date, final String defaultValue) {
        if (date == null) {
            return defaultValue;
        }
        return monthFormat.format(date);
    }

    /**
     * 获取日期范围内的月份集合
     *
     * @param start
     * @param end
     * @param splitSign
     * @return String[]
     */
    public static String[] getAllMonths(String start, String end, final String splitSign) {
        String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))"; // 判断YYYY-MM时间格式的正则表达式
        if (!start.matches(regex) || !end.matches(regex)) {
            return new String[0];
        }
        List<String> list = new ArrayList<String>();
        if (start.compareTo(end) > 0) {
            // start大于end日期时，互换
            String temp = start;
            start = end;
            end = temp;
        }
        String temp = start; // 从最小月份开始
        while (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
            list.add(temp); // 首先加上最小月份,接着计算下一个月份
            String[] arr = temp.split(splitSign);
            int year = Integer.valueOf(arr[0]);
            int month = Integer.valueOf(arr[1]) + 1;
            if (month > 12) {
                month = 1;
                year++;
            }
            if (month < 10) {// 补0操作
                temp = year + splitSign + "0" + month;
            } else {
                temp = year + splitSign + month;
            }
        }
        int size = list.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 获取日期对应的GMT时间
     * @param nowdate
     * @return
     */
    @SuppressWarnings("unused")
    public static Date getCurrentGMTDate(Date nowdate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        try {
            return parseDateTime(dateFormat.format(nowdate));
        } catch (ParseException e) {
            logger.error("获取当前GMT日期转换异常", e.getCause());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据时区转换为GMT时间
     *
     * @param timezone
     * @param date
     * @return
     */
    public static Date getGMTDateByTimezone(int timezone, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(new SimpleTimeZone(timezone, "GMT"));
        try {
            return parseDateTime(dateFormat.format(date));
        } catch (ParseException e) {
            logger.error("获取当前GMT日期转换异常", e.getCause());
            e.printStackTrace();
        }
        return null;
    }
}
