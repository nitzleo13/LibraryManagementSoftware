package com.bodhi.util;

import java.util.Calendar;

public class DateUtil {
	
	public static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}

	public static java.sql.Date getNextDueDate() {
		Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.DATE, +14);
	    java.util.Date today = cal.getTime();
	    return new java.sql.Date(today.getTime());
	}
	
}
