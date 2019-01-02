package com.xxx.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 时间工具类 */
public class DateUtils {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/** 获取当前时间字符串 */
	public final static String format(){
		return format(new Date(),YYYY_MM_DD_HH_MM_SS);
	}

	/** 根据date转换string类型 */
	public final static String format(Date date,String type){
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**  生成UNIX时间戳 */
	public static String getTimeStamp() {
		return Long.toString(new Date().getTime());
	}

	public static String getCurrentYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}

	public static String getCurrentMonth() {
		Calendar date = Calendar.getInstance();
		String month = String.valueOf(date.get(Calendar.MONTH)+1);
		return month;
	}

	public static String getCurrentDay() {
		Calendar date = Calendar.getInstance();
		String day = String.valueOf(date.get(Calendar.DATE));
		return day;
	}

	public static String getCurrentHour() {
		Calendar date = Calendar.getInstance();
		String hour = String.valueOf(date.get(Calendar.HOUR_OF_DAY));
		return hour;
	}

	public static String getCurrentMinute() {
		Calendar date = Calendar.getInstance();
		String minute = String.valueOf(date.get(Calendar.MINUTE));
		return minute;
	}

	public static String getCurrentSecond() {
		Calendar date = Calendar.getInstance();
		String second = String.valueOf(date.get(Calendar.SECOND));
		return second;
	}

}
