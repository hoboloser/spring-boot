package org.bin.schema.util.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Lock {
	
	private static volatile boolean isLocking = false;
	
	public synchronized boolean volock(){
		if(isLocking){
			return false;
		}
		isLocking = true;
		return true;
	}
	
	public synchronized void vounlock(){
		isLocking = false;
	}
	
	private static Map<String,String> map = new ConcurrentHashMap<String, String>();
	
	private static String WDFLAG = "LOCKING";
	
	public synchronized boolean mapLock(String key){
		if(map.containsKey(key)){
			return false;
		}
		map.put(key, WDFLAG);
		return true;
	}
	
	public void mapunLock(String key){
		map.remove(key);
	}
}
