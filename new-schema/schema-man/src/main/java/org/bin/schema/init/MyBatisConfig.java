//package org.bin.schema.init;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//
//@Configuration
//@AutoConfigureAfter({ DatasourceConfig.class })  
//@EnableTransactionManagement
//public class MyBatisConfig implements TransactionManagementConfigurer{
//	
//	@Autowired
//	private DataSource dataSource;
//	
//	@Autowired
//	private Environment env;
//	
//	@Bean(name = "sqlSessionFactory")
//	public SqlSessionFactory getSqlSessionFactory() throws Exception{
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setTypeAliasesPackage(env.getProperty("spring.mybatis.typeAliesPackage"));
//		bean.setDataSource(dataSource);
//		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("spring.mybatis.mappers-locations")));
//		
//		return bean.getObject();
//	}
//	
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//	       return new DataSourceTransactionManager(dataSource);
//	}
//}
