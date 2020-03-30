package com.github.polyang.tool.number;

/**
 * @Author polyang
 * @Description 基于Snowflake算法实现的数字序号生成器<p>
 * snowflake的结构如下(每部分用-分开):<p>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000<p>
 * 第一位为未使用，接下来的41位为毫秒级时间(41位的长度可以使用69年)，
 * 然后是5位datacenterId和5位workerId(10位的长度最多支持部署1024个节点） ，
 * 最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号）
 * 一共加起来刚好64位，为一个Long型。(转换成字符串长度为18)<p>
 * snowflake生成的ID整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和workerId作区分）
 * 并且效率较高。据说：snowflake每秒能够产生26万个ID。
 * @Date 2020/3/28 21:01
 **/
public class SnowflakeSequenceGenerator {

	/**
	 * 工作机器ID最大值，默认5L，0-31
	 */
	private long workerIdBits = 5L;
	/**
	 * 工作站ID最大值，默认5L,0-31
	 */
	private long datacenterIdBits = 5L;
	/**
	 * 工作机器ID
	 */
	private long workerId;
	/**
	 * 序列号
	 */
	private long datacenterId;
	
	private Snowflake snowflake;

	/**
	 * 初始化Snowflake
	 * @param workerIdBits	工作机器ID最大值，默认5L，0-31
	 * @param datacenterIdBits	工作站ID最大值，默认5L,0-31
	 * @param workerId	工作机器ID
	 * @param datacenterId	序列号
	 */
	public SnowflakeSequenceGenerator(long workerIdBits, long datacenterIdBits, long workerId, long datacenterId) {
		this.workerIdBits = workerIdBits;
		this.datacenterIdBits = datacenterIdBits;
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	public Long getSequence() {
		return getNumberSequence();
	}

	public long getNumberSequence() {
		return snowflake.nextId();
	}

	public long getWorkerIdBits() {
		return workerIdBits;
	}

	public void setWorkerIdBits(long workerIdBits) {
		this.workerIdBits = workerIdBits;
	}

	public long getDatacenterIdBits() {
		return datacenterIdBits;
	}

	public void setDatacenterIdBits(long datacenterIdBits) {
		this.datacenterIdBits = datacenterIdBits;
	}

	public long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}

	public long getDatacenterId() {
		return datacenterId;
	}

	public void setDatacenterId(long datacenterId) {
		this.datacenterId = datacenterId;
	}

	public Snowflake getSnowflake() {
		return snowflake;
	}

	public void setSnowflake(Snowflake snowflake) {
		this.snowflake = snowflake;
	}
	
}
