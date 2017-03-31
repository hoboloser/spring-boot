package org.bin.schema.entity;

import java.util.Date;

/**
 *  秒杀库存
 * @author Administrator
 *
 */
public class SeckillStock {

	/**
	 * 库存ID
	 */
	private long stockId;
	/**
	 * 秒杀内容
	 */
	private String name;
	/**
	 * 可秒杀数量
	 */
	private long number;
	/**
	 * 秒杀开启时间
	 */
	private Date startTime;
	/**
	 * 秒杀结束时间
	 */
	private Date endTime;
	
	private Date createTime;

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
