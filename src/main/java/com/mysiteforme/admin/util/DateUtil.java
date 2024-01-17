package com.mysiteforme.admin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	private static final Log logger = LogFactory.getLog(DateUtil.class);
	public static final String DEFAULT_FORMATE = "yyyy-MM-dd";
	public static final String DEFAULT_LONG_FORMATE = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FULL_FORMATE = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final TimeZone CURRENT_TIME_ZONE = TimeZone.getTimeZone("Asia/Shanghai");

	public static Date format(String string) {
		return format(string, "yyyy-MM-dd");
	}

	public static Date format(String string, String formatString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		try {
			return dateFormat.parse(string);
		} catch (ParseException e) {
			logger.error(e);
		}
		return null;
	}

	public static String format(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	public static String format(Date date, String formatString) {
		if (date == null)
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		try {
			return dateFormat.format(date);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public static boolean isSameDay(Date date1, Date date2) {
		String date1Str = format(date1);
		String date2Str = format(date2);
		return date1Str.equals(date2Str);
	}

	public static boolean isSameDayWithNull(Date date1, Date date2) {
		if ((date1 == null) && (date2 == null))
			return true;
		if ((date1 == null) || (date2 == null))
			return false;

		String date1Str = format(date1);
		String date2Str = format(date2);
		return date1Str.equals(date2Str);
	}

	public static Date now() {
		Calendar calendar = Calendar.getInstance(CURRENT_TIME_ZONE);
		return calendar.getTime();
	}

	public static long current() {
		Calendar calendar = Calendar.getInstance(CURRENT_TIME_ZONE);
		return calendar.getTime().getTime();
	}

	public static String currentDateTime() {
		return format(now(), "yyyy-MM-dd HH:mm:ss");
	}

	public static Date toDate(long time) {
		Calendar calendar = Calendar.getInstance(CURRENT_TIME_ZONE);
		Date date = calendar.getTime();
		date.setTime(time);
		return date;
	}

	public static Date addDate(Date date, int num) {
		Calendar calendar = Calendar.getInstance(CURRENT_TIME_ZONE);
		calendar.setTime(date);
		calendar.add(5, num);
		return calendar.getTime();
	}

	public static int daysBetween(Date sdate, Date bdate) {
		Calendar calendar = Calendar.getInstance(CURRENT_TIME_ZONE);
		calendar.setTime(sdate);
		long time1 = calendar.getTimeInMillis();
		calendar.setTime(bdate);
		long time2 = calendar.getTimeInMillis();
		long days = (time2 - time1) / 86400000L;
		return Integer.parseInt(String.valueOf(days));
	}

	public static long secondsBetween(Date sdate, Date bdate) {
		Calendar calendar = Calendar.getInstance(CURRENT_TIME_ZONE);
		calendar.setTime(sdate);
		long time1 = calendar.getTimeInMillis();
		calendar.setTime(bdate);
		long time2 = calendar.getTimeInMillis();
		long days = (time2 - time1) / 1000L;
		return Integer.parseInt(String.valueOf(days));
	}
	
	/**
     * 获取本月开始日期
     * @return String
     * **/
    public static String getMonthStart(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 00:00:00";
    }
    /**
     * 获取本月最后一天
     * @return String
     * **/
    public static String getMonthEnd(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 23:59:59";
    }
    /**
     * 获取本周的第一天
     * @return String
     * **/
    public static String getWeekStart(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 00:00:00";
    }
    /**
     * 获取本周的最后一天
     * @return String
     * **/
    public static String getWeekEnd(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 23:59:59";
    }
}