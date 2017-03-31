package org.bin.schema.dao;

import java.util.List;

import org.bin.schema.entity.SeckillOrder;

public interface SeckillOrderDAO {

	/**
	 * 添加订单
	 * @param seckillOrder
	 * @return
	 */
	public int insertSeckillOrder(SeckillOrder seckillOrder);
	/**
	 * 查询所有
	 * @return
	 */
	public List<SeckillOrder> getAll();
	
	/**
	 * 通过存储过程调用
	 * @param seckillOrder
	 * @return
	 */
	public int insertSeckillOrderWithProduce(SeckillOrder seckillOrder);
}
