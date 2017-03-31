package org.bin.schema.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class DateUtil {

	/**
	 * 判断2个日期是否在同一周 yes = true , no = false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeek(Date date1, Date date2) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date1);
		int lastweek = ca.WEEK_OF_YEAR;// 上一次提现时间所在周数

		ca.setTime(date2);
		int nowweek = ca.WEEK_OF_YEAR;

		if (lastweek == nowweek) {// 同一周
			return true;
		}
		return false;
	}

	/**
	 * 计算日期是星期几
	 * <p>
	 * 星期天 = 1
	 * </p>
	 * <p>
	 * 星期一 = 2
	 * </p>
	 * <p>
	 * 星期二 = 3
	 * </p>
	 * <p>
	 * 星期三 = 4
	 * </p>
	 * <p>
	 * 星期四 = 5
	 * </p>
	 * <p>
	 * 星期五 = 6
	 * </p>
	 * <p>
	 * 星期六 = 7
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public static int dayForWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		return weekday;
	}

	/**
	 * 计算日期是星期几
	 * <p>
	 * 星期天 = 1
	 * </p>
	 * <p>
	 * 星期一 = 2
	 * </p>
	 * <p>
	 * 星期二 = 3
	 * </p>
	 * <p>
	 * 星期三 = 4
	 * </p>
	 * <p>
	 * 星期四 = 5
	 * </p>
	 * <p>
	 * 星期五 = 6
	 * </p>
	 * <p>
	 * 星期六 = 7
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public static int dayForWeek(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date tmpDate;
		try {
			tmpDate = format.parse(date);
		} catch (ParseException e) {
			tmpDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(tmpDate);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		return weekday;
	}

	/**
	 * 计算日期date在hour:minute:seconds之前还是之后 -1 之前 1之后 0同一时间
	 * 
	 * @return
	 */
	public static int hourBetDay(Date date, int hour, int minute, int seconds) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, seconds);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);

		if (c.after(c1)) {
			return 1;
		}
		if (c.before(c1)) {
			return -1;
		}

		return 0;
	}

	public static String format(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss sss");
		return format.format(date);
	}

	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2
						.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}
	/**
	 * 1490858286195 06
	 * 1490858299495
	 * 1490858318321 38
	 * TODO
	 * @param args
	 */
//	public static void main(String[] args) {
//		System.out.println(new Date().getTime());
//		AtomicInteger count = new AtomicInteger();
//		while(true){
//			count.incrementAndGet();
//			System.out.println(count.get());
//		}
//		
//	}
}
