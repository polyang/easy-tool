package com.github.polyang.tool.encypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @Author polyang
 * @Description MD5加密工具类
 * @Date 2020/3/28 20:56
 **/
public class Md5Util {

	private static final String[] HEX_DIGITS = {"0","1","2","3","4","5","6","7",
											   "8","9","a","b","c","d","e","f"};
	/**
	 * MD5加密
	 * @param orgin	待加密的数据
	 * @return	加密后的数据
	 */
	public static String md5Encode(String orgin) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(orgin.getBytes(StandardCharsets.UTF_8));
			result = byteArrayToHexString(b).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static String byteArrayToHexString(byte[] b) {
		String result = "";
		StringBuilder sb = new StringBuilder();
		for (byte value : b) {
			sb.append(byteToHexString(value));
		}
		result = sb.toString();
		return result;
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if(n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
	}
	
}
