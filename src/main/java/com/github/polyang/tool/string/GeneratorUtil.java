package com.github.polyang.tool.string;

import java.util.UUID;

/**
 * @Author polyang
 * @Description Generator工具类
 * @Date 2020/3/28 21:05
 **/
public class GeneratorUtil {

	/**
	 * 获取uuid
	 * @return	去除了“-”的uuid字符串
	 */
	public static String getUUID() {
		String result = UUID.randomUUID().toString();
		result = result.replaceAll("-", "");
		return result;
	}
	
}
