package org.bin.schema.util.lock;

import redis.clients.jedis.Jedis;

/**
 * single class
 * <p>Title:JedisLock</p>
 * <p>Description:</p>
 * @author binH
 * @date 2017年3月23日 上午10:50:46
 */
public class JedisLock {

	private static long expireMsecs = 1000 * 60 * 1; // min 锁持有超时

	private JedisLock() {
		
	}
	
	private static class HolderClass { 
        private final static JedisLock instance = new JedisLock(); 
	} 
    
	public static JedisLock getInstance() { 
		return HolderClass.instance; 
	} 
	
	/**
	 * get redis lock
	 * @param jedis 
	 * @param lockKey 加锁key
	 * @param timeoutMsecs 超时毫秒数，不传 or 0 or <0 默认300ms 
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized boolean acquire(Jedis jedis,String lockKey,Integer timeoutMsecs)
			throws InterruptedException {
		if(null == timeoutMsecs || timeoutMsecs <= 0){
			timeoutMsecs = 300;
		}
		while (timeoutMsecs >= 0) {
			long expires = System.currentTimeMillis() + expireMsecs + 1;
			String expiresStr = String.valueOf(expires); // 锁到期时间
			if (jedis.setnx(lockKey, expiresStr) == 1) {
				return true;
			}
			String currentValueStr = jedis.get(lockKey); // redis里的时间
			// 表示已经锁失效，要重新设置锁
			if (currentValueStr != null
					&& Long.parseLong(currentValueStr) < System
							.currentTimeMillis()) {
				// 判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
				// lock is expired
				String oldValueStr = jedis.getSet(lockKey, expiresStr);
				// 获取上一个锁到期时间，并设置现在的锁到期时间，;
				// 只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
				if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
					// 如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
					return true;
				}
			}
			timeoutMsecs -= 100;
			Thread.sleep(100);
		}
		return false;
	}

	/**
	 * redis 锁释放
	 * @param jedis
	 * @param lockKey 加锁key
	 */
	public void unLock(Jedis jedis,String lockKey) {
		jedis.del(lockKey);
	}
}