package com.tkbaru.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonHelper {
	public static String todayDateToString() {
		String result = "";
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");

		Date today = Calendar.getInstance().getTime();        
		result = df.format(today);
	
		return result;
	}
	
	public static String todayDateToString(String formatDate) {
		String result = "";
		
		DateFormat df = new SimpleDateFormat(formatDate);

		Date today = Calendar.getInstance().getTime();        
		result = df.format(today);
	
		return result;
	}
}
