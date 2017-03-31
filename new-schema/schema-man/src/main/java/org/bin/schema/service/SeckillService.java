package org.bin.schema.service;

import java.util.List;

import org.bin.schema.entity.SeckillStock;
import org.bin.schema.exception.BussinessException;
import org.bin.schema.exception.SystemException;

public interface SeckillService {

	/**
	 * 查询所有秒杀产品
	 * @return
	 */
	public List<SeckillStock> queryAllSeckill();
	
	/**
	 * 查询单个秒杀产品信息
	 * @param seckillId
	 * @return
	 */
	public SeckillStock querySeckillByID(long seckillId);
	
	/**
	 * 输出秒杀接口
	 * @param seckillId
	 * @return
	 */
	public String printOutUrlWithMd5(long seckillId) throws BussinessException;
	
	/**
	 * 执行秒杀
	 * @return
	 */
	public void excuteSeckill(long seckillId,long userId,long userPhone,String md5) throws BussinessException,SystemException;
	public void excuteSeckillProduce(long seckillId,long userId,long userPhone,String md5) throws BussinessException,SystemException;
	
}
