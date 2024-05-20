package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.net.aso.p;

public class isValid {

	public static boolean regexStr(String str, String regex) {
		Pattern pt = Pattern.compile(regex);
		Matcher mt = pt.matcher(str);

		if (mt.find())
			return true;

		return false;
	}

	public static boolean idPassChk(String str) {
		String regex = "^[a-zA-Z0-9]{5,20}$";
		return regexStr(str, regex);
	}
	
	public static boolean nameChk(String str) {
		String regex = "^[가-힇]{2,6}$";
		return regexStr(str, regex);
	}
	
	public static boolean phoneChk(String str) {
		String regex = "^010-?\\d{4}-?\\d{4}$";
		return regexStr(str, regex);
	}
	
	public static boolean birthChk(String str) {
		String regex = "^[0-9]{6}$";
		return regexStr(str, regex);
	}

}
