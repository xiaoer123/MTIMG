package com.dehui.communication.remote.util;

import java.util.regex.Pattern;

public class StringUtils {

	public static String objToStr(Object obj) {
		if (obj == null || "".equals(obj.toString().trim())) {
			return null;
		}
		return obj.toString();
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

}
