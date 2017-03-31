package org.bin.schema.util.seril;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * <p>
 * Title:JedisUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author binH
 * @date 2017年3月23日 上午9:39:53
 */
public class JedisUtil {

	private static int MAX_ACTIVE = -1;

	private static int MAX_IDLE = 100;

	private static int MIN_IDLE = 1;

	private static int MAX_WAIT = 600;

//	private static int TIME_OUT = 6000;

	private static boolean TEST_ON_BORROW = false;

	private static String host = "127.0.0.1";

	private static int port = 6379;

	private static JedisPool jedisPool = null;

	private static void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(MAX_ACTIVE);
		config.setMaxIdle(MAX_IDLE);
		config.setMinIdle(MIN_IDLE);
		config.setMaxWait(MAX_WAIT);
		config.setTestOnBorrow(TEST_ON_BORROW);
		jedisPool = new JedisPool(host, port);
	}

	private static synchronized void poolInit() {
		if (jedisPool == null) {
			initPool();
		}
	}

	private static synchronized Jedis getJedis() {
		if (jedisPool == null) {
			poolInit();
		}
		if (jedisPool != null) {
			return jedisPool.getResource();
		}
		return null;
	}

	public static void put(Object key, Object value) {
		Jedis jedis = null;
		try{
			jedis = getJedis();
			jedis.setnx(ProtostuffUtil.serializer(key),
					ProtostuffUtil.serializer(value));
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	public static <T> T get(Object key, Class<T> clazz) {
		Jedis jedis = null;
		try{
			jedis = getJedis();
			byte[] value = jedis.get(ProtostuffUtil.serializer(key));
			if (null == value) {
				return null;
			}
			return ProtostuffUtil.deserializer(value, clazz);
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

}
