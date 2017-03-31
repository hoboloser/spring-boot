package org.bin.schema.dao;

import java.util.List;

import org.bin.schema.entity.SeckillStock;

public interface SeckillStockDAO {

	/**
	 * 添加库存
	 * @param seckillStock
	 * @return
	 */
	public int insertSeckillStock(SeckillStock seckillStock);
	/**
	 * 查询所有
	 * @return
	 */
	public List<SeckillStock> getAll();
	
	/**
	 * 查询一条记录
	 * @return
	 */
	public SeckillStock getStockById(long stockId);
	
	/**
	 * 减库存
	 * @return
	 */
	public int reduceSeckillStock(long id);
}
