package com.tkbaru.common;

import java.util.List;

public class Converter {
	public static String convertToCommaSeparated(List<Integer> intList) {
		String result = "";
		for (Integer i:intList) {
			result += Integer.toString(i) + ", ";
		}
		
		result = result.substring(0, result.length() - 1);
		
		return result;
	}
}
