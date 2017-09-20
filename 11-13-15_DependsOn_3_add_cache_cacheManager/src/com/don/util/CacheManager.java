package com.don.util;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class CacheManager implements BeanFactoryAware{

	private static CacheManager instance1;
	private Map<String,Object> dataMap;
	
	Properties cityIntrestProp = null;
	BeanFactory beanFactory; 
	
	
	@Override
	public void setBeanFactory(BeanFactory factory) throws BeansException {

		this.beanFactory = factory;
	}

	private CacheManager() {
		System.out.println("cacheManager constructor execute");
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
		Cache cache = null;
//		cache = beanFactory.getBean("cache",Cache.class);
		cache = Cache.getInstance();
		cityIntrestProp = new Properties();
		try {
			cityIntrestProp.load(this.getClass().getClassLoader().getResourceAsStream("city_ri.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	cityIntrestProp.
		dataMap.put("city_riProp",cityIntrestProp);
		cache.putAll(dataMap);
	}
}
