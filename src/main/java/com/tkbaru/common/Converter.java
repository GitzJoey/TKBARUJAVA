package com.tkbaru.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Converter {
	public static String convertToCommaSeparated(List<Integer> intList) {
		String result = "";
		for (Integer i:intList) {
			result += Integer.toString(i) + ", ";
		}
		
		result = result.substring(0, result.length() - 2);
		
		return result;
	}
	
	public static String convertToINClause(List<String> stringList) {
		String result = "";
		for (String s:stringList) {
			result += "'" + s + "'" + ", ";
		}
		
		result = result.substring(0, result.length() - 2);
		
		if (result.length() == 0) {
			return "('')";
		} else {
			result = "(" + result + ")";
		}
		
		return result;
	}	
	
	public static List<Integer> convertToIntegerList(String sInt) {
		List<Integer> result = new ArrayList<Integer>();
		
		if (sInt.length() == 0) return result;
		
		String[] items = sInt.split(",");
		
		for (String i:items) {
			if (i.length() == 0) { continue; }
			result.add(Integer.parseInt(i)); 
		}
		
		return result;
	}
	
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
