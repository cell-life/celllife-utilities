package org.celllife.utilities.publicholidays.service;

import java.util.Date;

import org.celllife.utilities.date.DateUtil;
import org.junit.Assert;
import org.junit.Test;

public class SouthAfricaPublicHolidayServiceTest {

	private SouthAfricanPublicHolidayService service = new SouthAfricanPublicHolidayService();
	
	@Test
	public void TestEaster2014() {
		Date goodFriday = DateUtil.createDate(18, 4, 2014);
		Date familyDay = DateUtil.createDate(21, 4, 2014);
		Assert.assertTrue(service.isPublicHoliday(goodFriday));
		Assert.assertTrue(service.isPublicHoliday(familyDay));
	}
	
	@Test
	public void TestEaster2015() {
		Date goodFriday = DateUtil.createDate(3, 4, 2015);
		Date familyDay = DateUtil.createDate(6, 4, 2015);
		Assert.assertTrue(service.isPublicHoliday(goodFriday));
		Assert.assertTrue(service.isPublicHoliday(familyDay));
	}
	
	@Test 
	public void TestSundayPublicHoliday() {
		// 27 April	Freedom Day is on a Sunday
		Date freedomDay = DateUtil.createDate(28, 4, 2014);
		Assert.assertTrue(service.isPublicHoliday(freedomDay));
	}

	@Test
	public void Test2January() {
		Date notPublicHoliday = DateUtil.createDate(2, 1, 2014);
		Assert.assertFalse(service.isPublicHoliday(notPublicHoliday));
	}
}
