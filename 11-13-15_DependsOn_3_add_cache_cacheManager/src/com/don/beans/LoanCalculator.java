package com.don.beans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.don.util.Cache;
import com.don.util.CacheManager;

public class LoanCalculator implements BeanFactoryAware{
	
	BeanFactory beanFactory ;
	
	
	
	
	@Override
	public void setBeanFactory(BeanFactory factory) throws BeansException {

		this.beanFactory = factory;
	}
	
	public double calculateIntrst(long priciple,int time, String city) throws FileNotFoundException, IOException, LoanNotApplicableException{
		
		float rateOfInterst=0.0f;
		double amount = 0.0;
		Cache cache = null;
		CacheManager manager = null; 
		
	//	manager = beanFactory.getBean("cacheManager",CacheManager.class);
		manager = CacheManager.getInstance();
		// intrest rate depends on city 
		// or all intrest rate specify in the one propertyfile 
		 
	//	cache = beanFactory.getBean("cache",Cache.class);
		cache = Cache.getInstance();
		
		Properties prop =(Properties) cache.get("city_riProp");
		if(prop.containsKey(city)==false){
			
			throw new LoanNotApplicableException();
		}
		else{
			rateOfInterst = Float.parseFloat((String) prop.get(city));
		}
		amount = (priciple*time*rateOfInterst)/100;
		return amount;
	}
	
}
// prog 1. 
// problem with this code read the  data  repetaedly  for every call tp the caller mathod 
// so we go for cache 
// we check in cache data available or not in cache if not available then read from  property file if available in property collection then ot data with key and value store in cache also. 

//---------------------------------

/*
 *  prog 2. 
 * 	problem - for ex. one more screen there 
 * 			for finding loan details of anu customomer. 
 * 			that time all details are required . 
 * 
 * so loan amount not onlu ist in loanCalculator but it required multiple place. 
 * so in this situation we also use cache
 * 
 * problem --- if any changes in the underling source(propertyfile) again cache will affected.
 * 	now our cache gives old value. 
 * 
 * 
 * so go for cacheManager 
 * 
 * now we rralze our loanCalculator are dependent on cache manager.
 * so first create cache managet then data populate 
 * after that create loanCalculator
 * 
 * 
 * now there is also problem 
 * 
 * we are not read the data from only one source instead we read the data from multiple sources.
 *  
 *  so if we write all logic in one class 
 *  
 *  its length and complicated and deficult, 
 *  
 *  so we write Multiple Accessor 
 *  
 * */
 