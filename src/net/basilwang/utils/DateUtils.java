package net.basilwang.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * 会继续开发
 * 
 * 例如:在当前时间上添加一分钟，一小时等等
 * @author WeiXiaoXing
 *
 */
public class DateUtils {
	private static Calendar cal = Calendar.getInstance();

	/**
	 * 获取具体到秒的时间
	 */
	private static SimpleDateFormat secondDateformat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/**
	 * 获取当前年份和月 例如 2013-07
	 */
	private static SimpleDateFormat yearAndMonthFormat = new SimpleDateFormat(
			"yyyy-MM");

	/**
	 * 
	 * 获取具体到秒的时间
	 * 
	 */
	public static String getCurrentSecondTime() {
		return secondDateformat.format(new Date());
	}

	/**
	 * 获取当前年份和月 格式例如 2013-07
	 */
	public static String getCurrentYearAndMonth() {
		return yearAndMonthFormat.format(new Date());
	}

	public static String getCurrentYear() {
		return String.valueOf(cal.get(Calendar.YEAR));
	}

	public static int getCurrentDayOfMonth() {
		return cal.get(Calendar.DAY_OF_MONTH);
	}
}
