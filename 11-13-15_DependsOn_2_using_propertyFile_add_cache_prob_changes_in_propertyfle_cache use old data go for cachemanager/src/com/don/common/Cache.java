package com.don.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
	private static Cache instance;
	private Map<String, Object> dataMap;

	private Cache(){
		dataMap = new ConcurrentHashMap<String, Object>();
		// no operation
	}
	public  static Cache getInstance(){
		
		if(instance == null ){
			instance = new Cache();
		}
		return instance;
	}
	
	public Object get(String key){
		System.out.println("get");
		Object object = dataMap.get(key);
		return object;
	}
	public Object put(String key,Object value){
		Object object = dataMap.put(key, value);
		System.out.println("put");
		return object;
	}
	public Boolean containsKey(String key){
		boolean bool = dataMap.containsKey(key);
		return bool;
	}
}
