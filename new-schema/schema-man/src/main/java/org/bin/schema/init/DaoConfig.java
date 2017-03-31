//package org.bin.schema.init;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.bin.schema.dao.util.MyBatisDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@AutoConfigureAfter({ MyBatisConfig.class })  
//public class DaoConfig {
//
//	@Autowired
//	public SqlSessionFactory sqlSessionFactory;
//	
//	@SuppressWarnings("rawtypes")
//	@Bean(name="myBatisDAO")
//	public MyBatisDAO getMyBatisDAO() throws Exception{
//		Class clazz = Class.forName("org.bin.schema.dao.util.MyBatisDAO");
//		MyBatisDAO mybatisDAO = (MyBatisDAO)clazz.newInstance();
//		mybatisDAO.setSqlSessionFactory(sqlSessionFactory);
//		return mybatisDAO;
//	}
//}
