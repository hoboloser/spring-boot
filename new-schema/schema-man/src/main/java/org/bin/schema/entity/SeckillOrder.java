package org.bin.schema.entity;

import java.util.Date;

/**
 * 秒杀订单
 * @author Administrator
 *
 */
public class SeckillOrder {

	/**
	 * 库存ID
	 */
	private long stockId;
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 用户手机号
	 */
	private long usePhone;
	/**
	 * 秒杀时间
	 */
	private Date seckillTime;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUsePhone() {
		return usePhone;
	}

	public void setUsePhone(long usePhone) {
		this.usePhone = usePhone;
	}

	public Date getSeckillTime() {
		return seckillTime;
	}

	public void setSeckillTime(Date seckillTime) {
		this.seckillTime = seckillTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
