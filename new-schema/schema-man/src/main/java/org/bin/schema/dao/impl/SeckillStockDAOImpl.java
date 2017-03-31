package org.bin.schema.dao.impl;

import java.util.List;

import org.bin.schema.dao.SeckillStockDAO;
import org.bin.schema.dao.util.SqlMapperID;
import org.bin.schema.entity.SeckillStock;
import org.springframework.stereotype.Repository;

@Repository(value = "seckillStockDAO")
public class SeckillStockDAOImpl extends BaseDAO<SeckillStock> implements SeckillStockDAO{

	@Override
	public int insertSeckillStock(SeckillStock seckillStock) {
		return super.insert(seckillStock, SqlMapperID.SeckillStockMapperID.class);
	}

	@Override
	public List<SeckillStock> getAll() {
		return super.queryAllForList(SqlMapperID.SeckillStockMapperID.class);
	}

	@Override
	public SeckillStock getStockById(long stockId) {
		return super.queryById(stockId, SqlMapperID.SeckillStockMapperID.class);
	}

	@Override
	public int reduceSeckillStock(long id) {
		return super.reduce(id, SqlMapperID.SeckillStockMapperID.class);
	}
	
}
