package org.celllife.utilities.publicholidays.service;

import java.util.Date;

/**
 * Service that determines if a given date is a public holiday 
 */
public interface PublicHolidayService {

	/**
	 * Determines if today is a public holiday
	 * @param date Date
	 * @return boolean, true if the specified date is a public holiday
	 */
    public boolean isPublicHoliday(Date date);

}
