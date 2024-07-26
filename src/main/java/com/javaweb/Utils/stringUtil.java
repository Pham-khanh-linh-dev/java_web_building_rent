package com.javaweb.Utils;

public class stringUtil {

	public static boolean checkString(String str) {
		if(str == null || str.isEmpty()) {
			return false;
		}
		return true;
	}
	public static String safeToString(Object obj) {
	    return obj != null ? obj.toString() : "";
	}
}
