package com.github.polyang.tool.string;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author polyang
 * @Description 字符串相关工具类
 * @Date 2020/3/28 21:06
 **/
public class StringUtil {

	/**
	 * 判断字符串是否为null
	 * @param param 参数
	 * @return true或者false
	 */
	public static boolean isNull(String param) {
		boolean bool = false;
		if(param == null) {
			bool = true;
		}
		return bool;
	}
	/**
	 * 判断字符串是否为null或者为空
	 * @param param 参数
	 * @return	true或者false
	 */
	public static boolean isBlank(String param) {
		boolean bool = false;
		if(param == null || "".equals(param)) {
			bool = true;
		}
		return bool;
	}
	/**
	 * 判断字符串是否不为null和空
	 * @param param
	 * @return
	 */
	public static boolean isNotBlank(String param) {
		boolean bool = false;
		if(param != null && !"".equals(param)) {
			bool = true;
		}
		return bool;
	}
	/**
	 * 如果字符串为null，则将其赋值为空字符串
	 * @param param	参数
	 * @return
	 */
	public static String processNull(String param) {
		if(param == null) {
			param = "";
		}
		return param;
	}
	/**
	 * 如果字符串为null，则将其赋值为指定的默认值
	 * @param param			参数
	 * @param defaultValue	默认值
	 * @return
	 */
	public static String processNull(String param, String defaultValue) {
		if(param == null) {
			param = defaultValue;
		}
		return param;
	}
	
	/**
	 * 是否包含中文字符
	 * @param param
	 * @return
	 */
	public static boolean isContainChinese(String param) {
		boolean bool = false;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(param);
		if(m.find()) {
			bool = true;
		}
		return bool;
	}
	
	/**
	 * 是否以中文字符开头
	 * @param param
	 * @return
	 */
	public static boolean isStartWithChinese(String param) {
		boolean bool = false;
		Pattern p = Pattern.compile("^[\u4e00-\u9fa5]");
		Matcher m = p.matcher(param);
		if(m.find()) {
			bool = true;
		}
		return bool;
	}
	/**
	 * <p>比较字符串是否相等
	 * <p>两者都为null时，将返回false
	 * @param str1	待比较的字符串
	 * @param str2	待比较的字符串
	 * @return	比较结果
	 */
	public static boolean equals(String str1, String str2) {
		if(str1 == null && str2 == null) {
			return false;
		}
		return Objects.equals(str1, str2);
	}
	/**
	 * <p>比较字符串是否相等
	 * <p>两者都为null时，将返回true
	 * @param str1	待比较的字符串
	 * @param str2	待比较的字符串
	 * @return	比较结果
	 */
	public static boolean equalsIgnoreNull(String str1, String str2) {
		return Objects.equals(str1, str2);
	}

	/**
	 * 不足位数时左边补充指定字符串
	 * @param param		目标字符串
	 * @param supply	要补充的字符串
	 * @param length	位数
	 * @return	符合位数的字符串
	 */
	public static String supplyLeft(String param, String supply, int length) {
		int len = param.length();
		if (len < length) {
			for (int index = 0; index < length - len; index++) {
				param = supply + param;
			}
		}
		return param;
	}

	/**
	 * 不足位数时右边补充指定字符串
	 * @param param		目标字符串
	 * @param supply	要补充的字符串
	 * @param length	位数
	 * @return	符合位数的字符串
	 */
	public static String supplyRight(String param, String supply, int length) {
		int len = param.length();
		if (len < length) {
			for (int index = 0; index < length - len; index++) {
				param = param + supply;
			}
		}
		return param;
	}

	/**
	 * 不足位数时左边补充0
	 * @param param		目标字符串
	 * @param length	位数
	 * @return	符合位数的字符串
	 */
	public static String supplyLeftZero(String param, int length) {
		return supplyLeft(param, "0", length);
	}

	/**
	 * 不足位数时右边补充0
	 * @param param		目标字符串
	 * @param length	位数
	 * @return	符合位数的字符串
	 */
	public static String supplyRightZero(String param, int length) {
		return supplyRight(param, "0", length);
	}

}
