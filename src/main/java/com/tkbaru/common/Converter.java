package com.tkbaru.common;

import java.util.ArrayList;
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
}
