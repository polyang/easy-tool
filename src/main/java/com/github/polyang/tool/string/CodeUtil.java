package com.github.polyang.tool.string;

import java.util.Random;

/**
 * @Author polyang
 * @Description 验证码工具类
 * @Date 2020/3/28 21:05
 **/
public class CodeUtil {

	private static final String[] ARR = {"1","2","3","4","5","6","7","8","9","a",
			 							 "b","d","e","f","g","h","j","m","n","p",
			 							 "q","r","t","y","A","B","D","E","F","G",
			 							 "H","J","M","N","P","Q","R","T","Y"};
	/**
	 * 获取三位数验证码
	 * @return
	 */
	public static String getThreeCode(){
		return getCode(3);
	}
	/**
	 * 获取四位数验证码
	 * @return
	 */
	public static String getFourCode(){
		return getCode(4);
	}
	/**
	 * 获取五位数验证码
	 * @return
	 */
	public static String getFiveCode(){
		return getCode(5);
	}
	/**
	 * 获取六位数验证码
	 * @return
	 */
	public static String getSixCode(){
		return getCode(6);
	}
	/**
	 * 获取指定位数的验证码
	 * @param num	验证码位数
	 * @return
	 */
	public static String getCode(int num){
		String result = "";
		Random r = new Random();
		int j = 0;
		for (int i = 0; i < num; i++) {
			// nextInt是从0开始的
			j = r.nextInt(39);
			result += ARR[j];
		}
		return result;
	}
	
	
}
