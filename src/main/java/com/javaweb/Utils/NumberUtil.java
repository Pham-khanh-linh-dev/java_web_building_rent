package com.javaweb.Utils;

public class NumberUtil {

	public static boolean isLong(String str) {
		try {
			Long check = Long.parseLong(str);
			
		} catch(Exception e){
			return false;
		}
		return true;
	}
}
