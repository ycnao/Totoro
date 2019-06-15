package com.nadia.totoro.util;

import java.util.regex.Pattern;

/**
 * Regex约束工具类
 * author: Created by 闹闹 on 2018/6/26
 * version: 1.0.0
 */
public class RegexUtil {
	
	public static final String NUMBER = "^[0-9]+$";
	public static final String PHONE = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}$";
	public static final String CKG_PATTERN = "^[1-9]\\d{1}[*][1-9]\\d{1}[*][1-9]\\d{1}$";
	public static final String USERNAME_PATTERN = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]{6,12}+$";
	
	public static final String RealName_PATTERN = "^[\\u4e00-\\u9fa5]{2,15}+$";
	public static final String RealName_Message = "真实姓名必须是2到15个汉字!";
	
	public static final String PASSWORD_LENGTH_PATTERN = ".{6,32}";
	public static final String PASSWORD_LENGTH_Message = "输入长度6-32位!";
	
	public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9_]+$";
	public static final String PASSWORD_Message = "只能是数字，字母组成！";
	
	public static final String Mobile_PATTERN = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8}$";
	public static final String Mobile_Message = "请输入正确的手机号码!";
	
	public static final String Home_Phone = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";
	public static final String Home_Phone_Message = "请输入正确的固定电话，如“0774-8881234”!";
	
	public static final String Emial_PATTERN = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	public static final String Emial_Message = "请输入正确的电子邮箱!";
	
	public static final String IDENTITY_CAR = "(^\\d{18}$)|(^\\d{17}(\\d|x|X)$)|(^\\d{15}$)";
	public static final String IDENTITY_CAR_Message = "请输入正确的身份证号码！";
	
	public static final String LICENSE_PATTERN = "^([\\u4e00-\\u9fa5]{1,2}[A-Za-z]{1}[.]{0,1})[0-9A-Za-z]{3,5}$";
	public static final String LICENSE_Message = "请输入正确的车牌号码!";
	
	
	public RegexUtil() {
	}
	
	public static Pattern getValidator(String Pattern_String) {
		return Pattern.compile(Pattern_String);
	}
	
	public static boolean Validate(String Pattern_String, String validateString) {
		try {
			return (Pattern_String != null && Pattern_String.length() > 0 && validateString != null) && getValidator(Pattern_String).matcher(validateString).matches();
		} catch (Exception var3) {
			return false;
		}
	}
}
