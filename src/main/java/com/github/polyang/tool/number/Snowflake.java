package com.github.polyang.tool.number;

/**
 * @Author polyang
 * @Description 分布式高效有序ID生产序列（基于snowflake算法实现）
 * @Date 2020/3/28 20:59
 **/
public class Snowflake {

	private final long twepoch = 1288834974657L;
	private long workerIdBits = 5L;
	private long datacenterIdBits = 5L;
	private long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private long sequenceBits = 12L;
	private long workerIdShift = sequenceBits;
	private long datacenterIdShift = sequenceBits + workerIdBits;
	private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long workerId;
	private long datacenterId;
	private long sequence = 0L;
	private long lastTimestamp = -1L;
	
	
	/**
	 * @param workerId 工作机器ID
	 * @param datacenterId 序列号
	 */
	public Snowflake(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(
					String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	/**
	 * 
	 * @param workerIdBits  工作机器ID最大值，默认5L，0-31
	 * @param datacenterIdBits 工作站ID最大值，默认5L,0-31
	 * @param workerId 工作机器ID
	 * @param datacenterId 序列号
	 */
	public Snowflake(long workerIdBits,long datacenterIdBits,long workerId, long datacenterId){
		this.workerIdBits = workerIdBits;
		this.datacenterIdBits = datacenterIdBits;
		this.maxWorkerId = -1L ^ (-1L << this.workerIdBits);
		this.maxDatacenterId = -1L ^ (-1L << this.datacenterIdBits);
		this.sequenceBits = 12L;
		this.workerIdShift = this.sequenceBits;
		this.datacenterIdShift = this.sequenceBits + this.workerIdBits;
		this.timestampLeftShift = this.sequenceBits + this.workerIdBits + this.datacenterIdBits;
		this.sequenceMask = -1L ^ (-1L << this.sequenceBits);
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(
					String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	/**
	 * 获取下一个ID
	 * @return
	 */
	public synchronized long nextId() {
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(String.format(
					"Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0L;
		}

		lastTimestamp = timestamp;

		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;
	}

	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen() {
		return SystemClock.now();
	}
	
}
