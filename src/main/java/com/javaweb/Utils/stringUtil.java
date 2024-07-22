package com.javaweb.Utils;

public class stringUtil {

	public static boolean checkString(String str) {
		if(str == null || str.isEmpty()) {
			return false;
		}
		return true;
	}
}
