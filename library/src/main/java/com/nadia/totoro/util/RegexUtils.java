package com.nadia.totoro.util;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 约束工具类
 * author: Created by 闹闹 on 2018/6/26
 * version: 1.0.0
 */
public class RegexUtils {
	
	/**
	 * 判断手机号是否符合规范
	 *
	 * @param phoneNo 输入的手机号
	 */
	public static boolean isTelephoneNumber(String phoneNo) {
		if (TextUtils.isEmpty(phoneNo)) {
			return false;
		}
		if (phoneNo.length() == 11) {
			for (int i = 0; i < 11; i++) {
				if (!PhoneNumberUtils.isISODigit(phoneNo.charAt(i))) {
					return false;
				}
			}
			Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" + "|(14[5,7])" + "|(15[^4,\\D])" + "|(17[3,6-8])" + "|(18[0-9]))\\d{8}$");
			Matcher m = p.matcher(phoneNo);
			return m.matches();
		}
		return false;
	}
	
	/**
	 * 判断邮箱是否合法
	 *
	 * @param email 邮箱
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email)) return false;
		//Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * 判断是不是电话号码
	 *
	 * @param phoneNumber
	 */
	public static boolean isPhoneNumberValid(String phoneNumber) {
		/*
		 * 可接受的电话格式有：
		 */
		String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
		/*
		 * 可接受的电话格式有：
		 */
		String expression2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(phoneNumber);
		
		Pattern pattern2 = Pattern.compile(expression2);
		Matcher matcher2 = pattern2.matcher(phoneNumber);
		return matcher.matches() || matcher2.matches();
	}
	
	/**
	 * 验证统一社会信用代码
	 *
	 * @param businessCode 统一社会信用代码
	 */
	public static boolean isBusinessCode(String businessCode) {
		if ((businessCode.equals("")) || businessCode.length() != 18) {
			return false;
		}
		String baseCode = "0123456789ABCDEFGHJKLMNPQRTUWXY";
		char[] baseCodeArray = baseCode.toCharArray();
		Map<Character, Integer> codes = new HashMap<Character, Integer>();
		for (int i = 0; i < baseCode.length(); i++) {
			codes.put(baseCodeArray[i], i);
		}
		char[] businessCodeArray = businessCode.toCharArray();
		char check = businessCodeArray[17];
		if (baseCode.indexOf(check) == -1) {
			return false;
		}
		int[] wi = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			char key = businessCodeArray[i];
			if (baseCode.indexOf(key) == -1) {
				return false;
			}
			sum += (codes.get(key) * wi[i]);
		}
		int value = 31 - sum % 31;
		return value == codes.get(check);
	}
	
	/**
	 * 纯数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (TextUtils.isEmpty(str)) {
			return false;
		}
		for (int i = str.length(); --i >= 0; ) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
