package com.core.util;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	
	public static Cache instance;
	public Map<String, Object> dataMap;
	
	private Cache(){
		dataMap = new HashMap<String, Object>();
	}
	
	public void put(String key,Object value){
		dataMap.put(key, value);
	}
	public Object get(String key){

		return dataMap.get(key);
	}
	public boolean containsKey(String key){
		
		return dataMap.containsKey(key);
	}
	public static Cache getInstance(){
		
		if(instance == null)
		{
			instance = new Cache();
		}
		return instance;
	}
}
