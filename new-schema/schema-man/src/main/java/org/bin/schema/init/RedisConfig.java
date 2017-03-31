//package org.bin.schema.init;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import redis.clients.jedis.Jedis;
//
//@Configuration
//public class RedisConfig {
//	
//	@Autowired
//	private Environment env;
//	
//	@Bean(name = "jedis")
//	public Jedis getJedis() throws Exception{
//		Jedis jedis = new Jedis(env.getProperty("redis.host"),Integer.valueOf(env.getProperty("redis.port")));
//		
//		String pwd = env.getProperty("redis.passwrod");
//		if(null != pwd && !"".equals(pwd)){
//			jedis.auth(env.getProperty("redis.passwrod"));
//		}
//		return jedis;
//	}
//	
//}
