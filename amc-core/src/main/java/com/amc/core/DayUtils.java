package com.amc.core;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DayUtils {

    /**
     * 将时间戳转为 yyyy-MM-dd 格式的时间字符
     *
     * @param timestamp
     * @return
     */
    public static String timestamptoDateString(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    /**
     * 获取当天零点的时间字符
     *
     * @return
     */
    public static String getCurrentStartTime() {
        // 获取 Calendar 实例
        Calendar calendar = Calendar.getInstance();
        // 设置时间为今天的零点
        // 将小时,分钟,秒,毫秒都设置为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // 获取 Date 对象
        Date zeroPoint = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(zeroPoint);
    }

    /**
     * 获取当天时间零点的时间戳
     *
     * @return
     */
    public static Long getCurrentStartLong() {
        // 获取 Calendar 实例
        Calendar calendar = Calendar.getInstance();
        // 设置时间为今天的零点
        // 将小时,分钟,秒,毫秒都设置为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当天23:59的时间字符
     *
     * @return
     */
    public static String getCurrentEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endCalendarTime = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(endCalendarTime);
    }

    /**
     * 获取当天时间23:59分的时间戳
     *
     * @return
     */
    public static Long getCurrentEndLong() {
        // 获取 Calendar 实例
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }
}
