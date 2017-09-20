package com.don.profiler;

import java.util.Map;

import com.don.accrssor.IAccessor;
import com.don.util.Cache;

public class BaseProfilerImpl implements IProfiler {

	protected Cache cache;
	protected Map<String, IAccessor> accessorMap;
	IAccessor accessor = null;	
	
	@Override
	public Object setData(String key, Object data) {
		
		boolean isFound =false; 
		cache.put(key, data);
		isFound = accessorMap.containsKey(key);
		if (isFound) {
			accessor.s
		}
		
		return null;
	}

	@Override
	public Object getData(String key) {
		

		boolean isFound = false;
		Object data = null ; 
		isFound = cache.containsKey(key);
		
		if(isFound == false){
			
			if(accessorMap.containsKey(key)== false){

				try {
					throw new ProfilerAccessException("no data found for the given key");
				} catch (ProfilerAccessException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				}
			}
			accessor = accessorMap.get(key);
			data = accessor.getData(key);
			cache.put(key, data);
		}
		
		
		return data;
	}

}
