package org.bin.schema.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.schema.dao.SeckillOrderDAO;
import org.bin.schema.dao.util.SqlMapperID;
import org.bin.schema.entity.SeckillOrder;
import org.springframework.stereotype.Repository;

@Repository(value = "seckillOrderDAO")
public class SeckillOrderDAOImpl extends BaseDAO<SeckillOrder> implements SeckillOrderDAO{

	@Override
	public int insertSeckillOrder(SeckillOrder seckillOrder) {
		return super.insert(seckillOrder, SqlMapperID.SeckillOrderMapperID.class);
	}

	@Override
	public List<SeckillOrder> getAll() {
		return super.queryAllForList(SqlMapperID.SeckillOrderMapperID.class);
	}

	@Override
	public int insertSeckillOrderWithProduce(SeckillOrder seckillOrder) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("stockId", seckillOrder.getStockId());
		param.put("userId", seckillOrder.getUserId());
		param.put("usePhone", seckillOrder.getUsePhone());
		param.put("seckillTime", seckillOrder.getSeckillTime());
		param.put("result", null);
		
		myBatisDAO.findForObject("insertOrderWithProduce", param);
		return (Integer)(param.get("result"));
	}
	
}
