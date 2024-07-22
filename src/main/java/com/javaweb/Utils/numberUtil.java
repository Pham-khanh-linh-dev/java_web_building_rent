package com.javaweb.Utils;

public class numberUtil {

	public static boolean isLong(String str) {
		try {
			Long check = Long.parseLong(str);
			
		} catch(Exception e){
			return false;
		}
		return true;
	}
}
