package com.github.polyang.tool.hex;

/**
 * @Author polyang
 * @Description 十六进制转换工具类
 * @Date 2020/3/28 20:59
 **/
public class HexUtil {

	/**
	 * 将二进制转换成16进制
	 * @param b 二进制byte数组
	 * @return	十六进制字符串
	 */
	public static String byte2ToHexStr(byte[] b) {
		String result = "";
		StringBuilder sb = new StringBuilder();
		for (byte value : b) {
			String hex = Integer.toHexString(value & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
        result = sb.toString();
        return result;
	}
	
	/**
	 * 将十六进制转化为二进制
	 * @param hexStr	十六进制字符串
	 * @return	二进制byte数组
	 */
	public static byte[] hexStrToByte2(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		} 
	    byte[] result = new byte[hexStr.length()/2];  
	    for (int i = 0;i< hexStr.length()/2; i++) {  
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
            result[i] = (byte) (high * 16 + low);  
	    }  
	    return result;
	}
	
}
