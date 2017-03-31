package org.bin.schema.service.impl;

import java.util.Date;
import java.util.List;

import org.bin.schema.dao.SeckillOrderDAO;
import org.bin.schema.dao.SeckillStockDAO;
import org.bin.schema.entity.SeckillOrder;
import org.bin.schema.entity.SeckillStock;
import org.bin.schema.enums.ExceptionEnum;
import org.bin.schema.exception.BussinessException;
import org.bin.schema.exception.SystemException;
import org.bin.schema.service.SeckillService;
import org.bin.schema.util.EncryOrDecry;
import org.bin.schema.util.seril.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "seckillService")
public class SeckillServiceImpl implements SeckillService{

	@Autowired
	private SeckillStockDAO seckillStockDAO;
	
	@Autowired
	private SeckillOrderDAO seckillOrderDAO;
	
	private static final String KEY = "!qaz@wsx#123$,./%poi^lkj&ygj*;'[\\](cvb)_+-~";
	
	private static String mdFive = null;
	
	@Override
	public List<SeckillStock> queryAllSeckill() {
		
		return seckillStockDAO.getAll();
	}

	@Override
	public SeckillStock querySeckillByID(long seckillId) {
		return seckillStockDAO.getStockById(seckillId);
	}

	@Override
	public String printOutUrlWithMd5(long seckillId) throws BussinessException{
		SeckillStock stock = JedisUtil.get(seckillId, SeckillStock.class);
		if(null == stock){
			SeckillStock st = querySeckillByID(seckillId);
			if(null == st){
				throw new BussinessException(ExceptionEnum.NULL);
			}
			JedisUtil.put(seckillId,st);
			
			stock = JedisUtil.get(seckillId, SeckillStock.class);
		}
		long now = new Date().getTime();
		Date end = stock.getEndTime();
		Date start = stock.getStartTime();
		if(now < start.getTime()){
			throw new BussinessException(ExceptionEnum.NOTSTART);
		}
		if(now > end.getTime()){
			throw new BussinessException(ExceptionEnum.ISEND);
		}
		
		String encKey = seckillId + KEY;
		String md5 = EncryOrDecry.md5Encry(encKey);
		
		return md5;
	}

	@Override
	@Transactional
	public void excuteSeckill(long seckillId, long userId, long userPhone,String md5) throws BussinessException,SystemException{
		String encKey = seckillId + KEY;
		String reMd5 = EncryOrDecry.md5Encry(encKey);
		if(!reMd5.equals(md5)){
			throw new BussinessException(ExceptionEnum.BECHANGE);
		}
		try{
			SeckillOrder seckillOrder = new SeckillOrder();
			seckillOrder.setStockId(seckillId);
			seckillOrder.setUserId(userId);
			seckillOrder.setUsePhone(userPhone);
			seckillOrder.setSeckillTime(new Date());
			int result = seckillOrderDAO.insertSeckillOrder(seckillOrder);
			if(result < 0){
				throw new BussinessException(ExceptionEnum.ORDEREXC);
			}else if(result == 0){
				throw new BussinessException(ExceptionEnum.REPEAT);
			}
			int sre = seckillStockDAO.reduceSeckillStock(seckillId);
			if(sre < 0){
				throw new BussinessException(ExceptionEnum.FAILEDEXC);
			}else if(sre == 0){
				throw new BussinessException(ExceptionEnum.FAILED);
			}
		}catch(BussinessException e){
			throw new BussinessException(ExceptionEnum.FAILED);
		}catch(Exception e) {
			throw new SystemException(ExceptionEnum.SYSTEMEXCEPTION);
		}
	}

	@Override
	public void excuteSeckillProduce(long seckillId, long userId,
			long userPhone, String md5) throws BussinessException,
			SystemException {
		String encKey = seckillId + KEY;
		String reMd5 = EncryOrDecry.md5Encry(encKey);
		if(!reMd5.equals(md5)){
			throw new BussinessException(ExceptionEnum.BECHANGE);
		}
		SeckillOrder seckillOrder = new SeckillOrder();
		seckillOrder.setStockId(seckillId);
		seckillOrder.setUserId(userId);
		seckillOrder.setUsePhone(userPhone);
		seckillOrder.setSeckillTime(new Date());
		try{
			int result = seckillOrderDAO.insertSeckillOrderWithProduce(seckillOrder);
			if(result < 0){
				throw new BussinessException(ExceptionEnum.ORDEREXC);
			}else if(result == 0){
				throw new BussinessException(ExceptionEnum.REPEAT);
			}
		}catch(BussinessException e){
			throw new BussinessException(ExceptionEnum.FAILED);
		}catch(Exception e) {
			throw new SystemException(ExceptionEnum.SYSTEMEXCEPTION);
		}
	}
}
