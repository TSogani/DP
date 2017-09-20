package com.don.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.don.accrssor.CityRIPropertiAccessor;
import com.don.accrssor.IAccessor;

public class CacheManager implements BeanFactoryAware{

	private static CacheManager instance1;
	private Cache cache;

	private Map<String,Object> dataMap;
	
	Properties cityIntrestProp = null;
	List<IAccessor> accessors;
	BeanFactory beanFactory; 
	
	
	@Override
	public void setBeanFactory(BeanFactory factory) throws BeansException {

		this.beanFactory = factory;
	}

	private CacheManager() {
		System.out.println("cacheManager constructor execute");
		
		accessors = new ArrayList<IAccessor>();
		accessors.add(new CityRIPropertiAccessor());
		
		dataMap = new ConcurrentHashMap<String, Object>();
		init();
		// no opp
	}
	
	public synchronized static CacheManager getInstance(){
		if(instance1 == null){
			instance1 = new CacheManager();
		}
		return instance1;
	}
	
	

	public void init(){
		//System.out.println(accessors.size());
		for (IAccessor access : accessors) {
			String key = access.getKey();
			Object data = access.getData();
		//	System.out.println(key);
		//	System.out.println(data);
			dataMap.put(key,data);
		}
		
		cache = Cache.getInstance();
		cache.putAll(dataMap);
	}
}
