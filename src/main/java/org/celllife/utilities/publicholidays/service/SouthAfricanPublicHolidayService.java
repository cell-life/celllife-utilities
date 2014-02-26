package org.celllife.utilities.publicholidays.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.celllife.utilities.date.DateUtil;
import org.springframework.stereotype.Service;

/**
 * South African implementation of the PublicHoliday service.
 * 
 * These include:
 * New Year's Day
 * Human Rights Day
 * Good Friday (Friday before Easter Sunday)
 * Family Day (Monday after Easter Sunday)
 * Freedom Day
 * Workers' Day
 * Youth Day
 * National Women's Day
 * Heritage Day
 * Day of Reconciliation
 * Christmas Day
 * Day of Goodwill
 * 
 * If a public holiday falls on a Sunday, the following Monday is the public holiday.
 *  
 * See more here: http://www.gov.za/aboutsa/holidays.htm
 */
@Service("SouthAfricanPublicHolidayService")
public class SouthAfricanPublicHolidayService implements PublicHolidayService {

	Map<Integer, List<Date>> publicHolidayCache = new HashMap<Integer, List<Date>>();

	@Override
	public boolean isPublicHoliday(Date date) {
		Integer year = getYear(date);
		List<Date> publicHolidays = getPublicHolidays(year);
		return publicHolidays.contains(date);
	}
	
	private Integer getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	private List<Date> getPublicHolidays(Integer year) {
		List<Date> publicHolidays = publicHolidayCache.get(year);
		if (publicHolidays == null) {
			publicHolidays = createPublicHolidays(year);
			publicHolidayCache.put(year, publicHolidays);
		}
		return publicHolidays;
	}
	
	private List<Date> createPublicHolidays(Integer year) {
		List<Date> publicHolidays = new ArrayList<Date>();
		publicHolidays.add(createPublicHoliday(1, 1, year)); //1 January
		publicHolidays.add(createPublicHoliday(21, 3, year)); // 21 March
		Date easterSunday = getEasterSunday(year);
		publicHolidays.add(getGoodFriday(easterSunday)); // Good Friday
		publicHolidays.add(getFamilyDay(easterSunday)); // Easter Monday
		publicHolidays.add(createPublicHoliday(27, 4, year)); // 21 April
		publicHolidays.add(createPublicHoliday(1, 5, year)); // 1 May
		publicHolidays.add(createPublicHoliday(16, 6, year)); // 16 June
		publicHolidays.add(createPublicHoliday(9, 8, year)); // 9 August
		publicHolidays.add(createPublicHoliday(24, 9, year)); // 24 September
		publicHolidays.add(createPublicHoliday(16, 12, year)); // 16 December
		publicHolidays.add(createPublicHoliday(25, 12, year)); // 25 December
		publicHolidays.add(createPublicHoliday(26, 12, year)); // 26 December
		return publicHolidays;
	}
	
	// see: http://www.smart.net/~mmontes/nature1876.html
	private Date getEasterSunday(Integer year) {
		int a=year%19;
		int b=year/100;
		int c=year%100;
		int d=b/4;
		int e=b%4;
		int f=(b+8)/25;
		int g=(b-f+1)/3;
		int h=(19*a+b-d-g+15)%30;
		int i=c/4;
		int k=c%4;
		int l=(32+2*e+2*i-h-k)%7;
		int m=(a+11*h+22*l)/451;
		int p=(h+l-7*m+114)%31;
		
		int easter_month = (h+l-7*m+114)/31;
		int easter_day = p+1;
		
		return DateUtil.createDate(easter_day, easter_month, year);
	}
	
	private Date getGoodFriday(Date easterSunday) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(easterSunday);
		cal.add(Calendar.DAY_OF_YEAR, -2);
		return cal.getTime();
	}

	private Date getFamilyDay(Date easterSunday) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(easterSunday);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}
	
	private Date createPublicHoliday(Integer day, Integer month, Integer year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.createDate(day, month, year));
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		return cal.getTime();
	}
}
