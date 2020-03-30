package com.github.polyang.tool.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

/**
 * @Author polyang
 * @Description 读取classpath根目录下的properties文件，按先后顺序查找
 * <p>common.properties和project.properties文件，找到其中一个即停止
 * @Date 2020/3/28 21:04
 **/
public class ReadProperties {

	private static Map<String, String> map = null;
	
	/**
	 * 根据key获取properties文件中的值
	 * @param key
	 * @return
	 */
	public static String get(String key){
		if(map == null) {
			load();
		}
		return map.get(key);
	}
	/**
	 * 根据key获取properties文件中的值
	 * @param key
	 * @return
	 */
	public static Integer getInt(String key){
		if(map == null) {
			load();
		}
		String value = map.get(key);
		return Integer.parseInt(value);
	}
	
	synchronized static private void load(){
		map = new HashMap<String, String>();
		Properties props = null;
		InputStream in = null;
		try {
			props = new Properties();
			in = ReadProperties.class.getClassLoader().getResourceAsStream("common.properties");
			if(in == null) {
				in = ReadProperties.class.getClassLoader().getResourceAsStream("project.properties");
			}
			if(in == null) {
				throw new Exception("在classpath根目录下找不到common.properties或者project.properties文件");
			}
			props.load(new InputStreamReader(in, "utf-8"));
			Set<Entry<Object, Object>> entries = props.entrySet();
			Iterator<Entry<Object, Object>> it =  entries.iterator();
			Entry<Object, Object> entry = null;
			String key = null;
			String value = null;
			while(it.hasNext()){
				entry = it.next();
				key = (String)entry.getKey();
				value = (String)entry.getValue();
				map.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != in) {
                    in.close();
                }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
