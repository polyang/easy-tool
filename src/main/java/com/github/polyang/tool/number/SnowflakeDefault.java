package com.github.polyang.tool.number;

/**
 * @Author polyang
 * @Description 	默认Snowflake算法数字序号生成器<br>
 *     				workerIdBits（工作机器ID最大值）：5L
 *     				datacenterIdBits（工作站ID最大值）：5L
 *     				workerId（工作机器ID）：1L
 *     				datacenterId（序列号）：1L
 * @Date 2020/3/28 21:00
 **/
public class SnowflakeDefault {

	private static Snowflake snowflake = new Snowflake(5L, 5L, 1L, 1L);
	
	/**
	 * 获取下一个id
	 * @return
	 */
	public static long getNumberSequence() {
		return snowflake.nextId();
	}
	
	/**
	 * 获取下一个id
	 * @return
	 */
	public static String getNextId() {
		long id = snowflake.nextId();
		return Long.toString(id);
	}
	
}
