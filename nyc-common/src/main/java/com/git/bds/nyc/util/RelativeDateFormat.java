package com.git.bds.nyc.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 成大事
 * @since 2022/8/15 11:53
 * 发布时间格式化工具类
 */
public class RelativeDateFormat {
    /**
     * 一分钟
     */
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE = 1L;
    private static final long DAY = 24L;
    /**
     * 一小时
     */
    private static final long ONE_HOUR = 3600000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";

    /**
     * 时间格式化
     */
    public static String format(Date date) {
        // 计算出相差天数
        int i = differentDays(date, new Date());
        // 同一天
        if (0 == i) {
            // 计算出时间差
            long delta = System.currentTimeMillis() - date.getTime();
            // 小于一分钟
            if (delta < ONE * ONE_MINUTE) {
                long seconds = toSeconds(delta);
                return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
            }
            // 小于一小时
            else if (delta < ONE * ONE_HOUR) {
                long minutes = toMinutes(delta);
                return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
            }
            // 小于24小时
            else if (delta <  DAY * ONE_HOUR) {
                long hours = toHours(delta);
                return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
            }
        }
        // 不同一天
        else {
            if (1 == i) {
                return "昨天";
            }
            // 几天前
            else if (3 >= i) {
                return i + ONE_DAY_AGO;
            }
        }
        // 格式化时间
        return getYmdHm(date);
    }

    /**
     * 获取秒
     */
    private static long toSeconds(long date) {
        return date / 1000L;
    }

    /**
     * 获取分钟
     */
    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    /**
     * 获取小时
     */
    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    /**
     * 时间格式化，yyyy-MM-dd HH:mm
     */
    public static String getYmdHm(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(date);
    }

    /**
     * 获取天数
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        // 不同一年
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                //闰年
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    timeDistance += 366;
                }
                //不是闰年
                else {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        }
        // 同一年
        else {
            return day2 - day1;
        }
    }
}
