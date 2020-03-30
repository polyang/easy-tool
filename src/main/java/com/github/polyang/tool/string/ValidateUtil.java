package com.github.polyang.tool.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author polyang
 * @Description 正则表达式校验工具类
 * @Date 2020/3/28 21:06
 **/
public class ValidateUtil {

	/**
	 * 判断是否为手机号（已匹配新号段166/198/199）
	 * @param phone	手机号
	 * @return	是否匹配
	 */
	public static boolean isPhone(String phone) {
		if(StringUtil.isBlank(phone)) {
			return false;
		}
		if(phone.length() != 11) {
			return false;
		}
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
		return m.matches();
	}
	
	/**
	 * 判断是否为邮箱
	 * @param email	邮箱地址
	 * @return	是否匹配
	 */
	public static boolean isEmail(String email) {
		if(StringUtil.isBlank(email)) {
			return false;
		}
		String regex = "\\w+@(\\w+\\.){1,3}\\w+";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
		return m.matches();
	}
	
}
