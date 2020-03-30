package com.github.polyang.tool.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author polyang
 * @Description 日期工具类
 * @Date 2020/3/28 20:49
 **/
public class DateUtil {

	/**
	 * 获取当前时间
	 * @return 当前时间
	 */
	public static Date getDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	/**
	 * 获取yyyy-MM-dd格式当前时间
	 * @return 当前时间
	 */
	public static String getNowDateStr() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		return formatStrDate(date);
	}
	/**
	 * 获取yyyy-MM-dd HH:mm:ss格式当前时间
	 * @return 当前时间
	 */
	public static String getNowDateTimeStr() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		return formatStrDateTime(date);
	}
	/**
	 * 获取当前时间毫秒数
	 * @return 当前时间毫秒数
	 */
	public static long getTimeMillis() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		return date.getTime();
	}
	/**
	 * 获取yyyyMMdd格式当前时间
	 * @return 当前时间
	 */
	public static String get8Date() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	/**
	 * 获取yyyyMMdd格式的时间
	 * @param date	目标时间
	 * @return	yyyyMMdd格式的字符串时间
	 */
	public static String get8Date(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	/**
	 * 获取yyyyMMddHHmmss格式当前时间
	 * @return yyyyMMddHHmmss格式当前时间字符串
	 */
	public static String get14Date() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	/**
	 * 获取yyyyMMddHHmmss格式的时间
	 * @param date 目标时间
	 * @return yyyyMMddHHmmss格式的时间
	 */
	public static String get14Date(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	/**
	 * 将日期格式化为yyyy-MM-dd格式字符串
	 * @param date 目标时间
	 * @return yyyy-MM-dd格式字符串
	 */
	public static String formatStrDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	/**
	 * 将日期格式化为yyyy-MM-dd HH:mm:ss格式字符串
	 * @param date 目标时间
	 * @return yyyy-MM-dd HH:mm:ss格式字符串
	 */
	public static String formatStrDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	/**
	 * 字符串转日期（默认转为“yyyy-MM-dd”格式）
	 * @param dateStr 字符串日期
	 * @return	日期
	 */
	public static Date parseDate(String dateStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 字符串转日期（默认转为“yyyy-MM-dd HH:mm:ss”格式）
	 * @param dateStr 字符串日期
	 * @return 日期
	 */
	public static Date parseDateTime(String dateStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 字符串转日期
	 * @param dateStr	字符串日期
	 * @param pattern	字符串日期格式
	 * @return	日期
	 */
	public static Date parse(String dateStr, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 日期增加或减去年份
	 * @param date	日期
	 * @param year	要加减的年数
	 * @return	日期
	 */
	public static Date addYears(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}
	/**
	 * 日期增加或减去月份
	 * @param date	日期
	 * @param month 要加减的月数
	 * @return	日期
	 */
	public static Date addMonths(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}
	/**
	 * 日期增加或减去天数
	 * @param date	日期
	 * @param day	要加减的天数
	 * @return	日期
	 */
	public static Date addDays(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}
	/**
	 * 日期增加或减去小时数
	 * @param date	日期
	 * @param hour	要加减的小时数
	 * @return	日期
	 */
	public static Date addHours(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}
	/**
	 * 日期增加或减去分钟数
	 * @param date		日期
	 * @param minute	要加减的分钟数
	 * @return	日期
	 */
	public static Date addMinutes(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
}
