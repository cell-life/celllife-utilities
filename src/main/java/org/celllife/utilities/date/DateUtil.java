package org.celllife.utilities.date;

import java.util.Calendar;
import java.util.Date;

/**
 * Date Utility class with discrete functions that assist with creating and manipulating of dates
 */
public class DateUtil {

	public static Date createDate(Integer day, Integer month, Integer year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
