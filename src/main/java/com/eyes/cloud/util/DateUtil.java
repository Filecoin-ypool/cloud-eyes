package com.eyes.cloud.util;

import com.eyes.cloud.exception.BusinessException;
import lombok.Getter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 最近的五分钟
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getNear5(LocalDateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        int minuteOld = dateTime.getMinute();
        int minuteNew = minuteOld / 5 * 5;
        //如果正好是整5分钟,则取上一个5分钟
        if (minuteNew != 0 && minuteNew == minuteOld) {
            minuteNew -= 5;
        } else if (minuteNew == 0 && minuteNew == minuteOld) {
            return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getHour() - 1, 55, 0);
        }
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getHour(), minuteNew, 0);
    }
    /**
     * 最近的五秒
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getNear5s(LocalDateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        int minuteOld = dateTime.getSecond();
        int minuteNew = minuteOld / 5 * 5;
        //如果正好是整5分钟,则取上一个5分钟
        if (minuteNew != 0 && minuteNew == minuteOld) {
            minuteNew -= 5;
        } else if (minuteNew == 0 && minuteNew == minuteOld) {
            return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute()-1, 55);
        }
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute(), minuteNew);
    }

    /**
     * 最近的30秒
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getNear30s(LocalDateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        int minuteOld = dateTime.getSecond();
        int minuteNew = minuteOld / 30 * 30;
        if (minuteNew != 0 && minuteNew == minuteOld) {
            minuteNew -= 30;
        } else if (minuteNew == 0 && minuteNew == minuteOld) {
            return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute()-1, 30);
        }
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute(), minuteNew);
    }

    /**
     * 获取日期当天的最后一秒
     */
    public static Date getLastSecondOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取日期当天的第一秒
     */
    public static Date getFirstSecondOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Timestamp getStr2Timestamp(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(str);
            return new Timestamp(parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String getDateByTimestamp(Timestamp timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp.getTime());
        return simpleDateFormat.format(date);
    }

    public static Date getLastSecondOfDate(String time, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getLastSecondOfDate(date);
    }

    public static Date getFirstSecondOfDate(String time, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getFirstSecondOfDate(date);
    }

    /**
     * 根据年 月 获取对应的月份的开始时间,月份从0开始
     */
    public static Date getStartTimeOfMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(year, month, 1, 0, 0, 0);
        return a.getTime();
    }

    /**
     * 根据年 月 获取对应的月份的结束时间,月份从0开始
     */
    public static Date getEndTimeOfMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(year, month, 1, 0, 0, 0);
        a.set(Calendar.MONTH, month + 1);
        a.set(Calendar.SECOND, a.get(Calendar.SECOND) - 1);
        return a.getTime();
    }

    /**
     * 根据年 月 获取对应的月份 天数,月份从0开始
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 获取相差时间，不足一天按一天算
     */
    public static int getDifferTime(Timestamp start, Timestamp end) {
        Long days = Math.abs(start.getTime() - end.getTime()) / (1000 * 60 * 60 * 24);
        long restDay = Math.abs(start.getTime() - end.getTime()) % (1000 * 60 * 60 * 24);
        if (restDay != 0) {
            days++;
        }
        return days.intValue();
    }

    /**
     * 判断是否是每月最后一天
     *
     * @param date
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int leastMaximum = calendar.getActualMaximum(Calendar.DATE);
        int d = calendar.get(Calendar.DATE);
        if (leastMaximum == d) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateString = sdf.format(date);
        return dateString;
    }


    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static Integer getCurrentDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return Integer.valueOf(format.format(System.currentTimeMillis()));
    }


    /**
     * 获取当前分钟时间
     *
     * @return
     */
    public static Long getTimeByTimestamp(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date(timestamp);
        String time = sdf.format(date);
        return Long.valueOf(time);
    }

    /**
     * 获取当前分钟时间
     *
     * @return
     */
    public static Long getTimeHourByTimestamp(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        Date date = new Date(timestamp);
        String time = sdf.format(date);
        return Long.valueOf(time);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Long getTimeDayByTimestamp(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(timestamp);
        String time = sdf.format(date);
        return Long.valueOf(time);
    }

    /**
     * 获取当前月数时间
     *
     * @return
     */
    public static Integer getCurrentMonthTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar beforeTime = Calendar.getInstance();
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return Integer.valueOf(time);
    }

    /**
     * 获取昨日
     *
     * @return
     */
    public static Integer getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        return Integer.valueOf(yesterday);
    }

    /**
     * 获取上月开始时间
     *
     * @return
     */
    public static Date getBeforeFirstMonthdate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取上月截止时间
     *
     * @return
     */
    public static Date getBeforeLastMonthdate() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        return calendar.getTime();
    }

    /**
     * 获取今日最后一分钟数
     *
     * @return
     */
    public static Long getLastMinuteToday() {
        Date lastSecondOfDate = getLastSecondOfDate(new Date());
        Long timeByTimestamp = getTimeByTimestamp(lastSecondOfDate.getTime());
        return timeByTimestamp;
    }

    /**
     * 字符转转为时间
     *
     * @param str
     * @return
     */
    public static Date getDay2Date(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        return strtodate;
    }

    /**
     * 时间转换
     *
     * @param str
     * @return
     */
    public static Date getDateByStr(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = formatter.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        return time;
    }


    /**
     * 根据出生日期获取年龄
     *
     * @param birth
     * @return
     */
    public static int getAgeByBirth(int birth) {
        String now = convertByDate(new Date(), Format.YYYYMMDD);
        int n = Integer.parseInt(now);
        return (n - birth) / 10000;
    }

    @Getter
    public enum Format {
        //时间格式
        YYYY("yyyy", "\\d{4}"),
        YYYYMM("yyyyMM", "\\d{6}"),
        YYYYMMDD("yyyyMMdd", "\\d{8}"),
        YYYY_MM_DD("yyyy-MM-dd", "\\d{4}-\\d{2}-\\d{2}"),
        YYYY_MM("yyyy-MM", "\\d{4}-\\d{2}"),
        YYYYMMDDHHMMSS("yyyyMMddHHmmss", "\\d{14}"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"),
        HH_MM_SS("HH:mm:ss", "\\d{2}:\\d{2}:\\d{2}"),
        HHMMSS("HHmmss", "\\d{6}");
        private String format;
        private String reg;

        Format(String format, String reg) {
            this.format = format;
            this.reg = reg;
        }
    }

    /**
     * 根据字符串获取日期
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date convertByStr(String dateStr, DateUtil.Format format) throws ParseException {
        String reg = format.getReg();
        boolean valid = dateStr.matches(reg);
        if (valid) {
            DateFormat dateFormat = new SimpleDateFormat(format.getFormat());
            dateFormat.setLenient(false);
            return dateFormat.parse(dateStr);
        }
        throw new BusinessException("日期格式错误");
    }

    /**
     * 根据日期获取字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String convertByDate(Date date, DateUtil.Format format) {
        DateFormat dateFormat = new SimpleDateFormat(format.getFormat());
        return dateFormat.format(date);
    }

    /**
     * 严格检验时间格式
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static boolean checkDateStr(String dateStr, DateUtil.Format format) {
        try {
            convertByStr(dateStr, format);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}