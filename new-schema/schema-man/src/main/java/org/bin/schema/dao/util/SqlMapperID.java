package org.bin.schema.dao.util;

public class SqlMapperID {

	public class SeckillOrderMapperID{
		
		public static final String INSERT = "insertSeckillOrder";
		
		public static final String QUERY_ALL = "getSuccessKilled";
		
	}
	
	public class SeckillStockMapperID{
		
		public static final String INSERT = "insertSeckillStock";
		
		public static final String UPDATE = "updateSeckillStock";
		
		public static final String QUERY_ALL = "getSeckillStock";
		
		public static final String QUERY_BY_ID = "getSeckillStockById";
		
		public static final String UPDATE_REDUCE = "reduceSeckillStockById";
		
	}
	
	public class UserAccountMapperID{
		
		public static final String INSERT = "insertUserAccount";
		
		public static final String QUERY_ALL = "getUserAccount";
		
	}
}
