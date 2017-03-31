//package org.bin.schema.init;
//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//
//@Configuration
//public class DatasourceConfig{
//
//	@Autowired
//	private Environment env;
//	
//	@Bean(name = "dataSource",destroyMethod = "close", initMethod="init")
//	public DataSource getDataSource(){
//		Properties pro = new Properties();
//		
//		pro.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
//		pro.put("url", env.getProperty("spring.datasource.driver-class-url"));
//		pro.put("username", env.getProperty("spring.datasource.username"));
//		pro.put("password", env.getProperty("spring.datasource.password"));
//		pro.put("initialSize", env.getProperty("spring.datasource.connection-initial-size"));
//		pro.put("minIdle", env.getProperty("spring.datasource.connection-minimum-size"));
//		pro.put("maxActive", env.getProperty("spring.datasource.connection-maximum-size"));
//		pro.put("maxWait", env.getProperty("spring.datasource.connection-maxwait-time"));
//		pro.put("timeBetweenEvictionRunsMillis", env.getProperty("spring.datasource.connection-maxactive-time"));
//		pro.put("minEvictableIdleTimeMillis", env.getProperty("spring.datasource.connection-minlive-time"));
//		
//		try {
//			return DruidDataSourceFactory.createDataSource(pro);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//
//}
