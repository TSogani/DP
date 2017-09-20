package com.don.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

public class LoanCalculator {
	public double calculateIntrst(long priciple,int time, String city) throws FileNotFoundException, IOException, LoanNotApplicableException{

	// We can write this code in this way. 	
	// but there reading the data that  code shode be place in cache also in onw method. 
	// bt cache ment for store only not for loading. 	
	// so we go for cacheManager
		
		float rateOfInterst=0.0f;
		double amount = 0.0;
		// intrest rate depends on city 
		// or all intrest rate specify in the one propertyfile 
		Properties cityIntrestProp = null; 
		Cache cache = null;
		// read the data from properties
		cityIntrestProp = new Properties();
		
		
		cache = Cache.getInstance();
		if(cache.containsKey(city)==false){
			// load into properties collection
			cityIntrestProp.load(this.getClass().getClassLoader().getResourceAsStream("city_ri.properties"));
			
			// varify that given city available in property file or not
			if(cityIntrestProp.containsKey(city)){
				rateOfInterst = Float.parseFloat(cityIntrestProp.getProperty(city));
				// available then put into cache
				Object put = cache.put(city, rateOfInterst);
				//System.out.println("---"+put); if value already available then return replace value otherwise return null 
			}else{
				//System.out.println("for this city loan are not applicatble");
				throw new LoanNotApplicableException(); 
			}
		}
		else{
			rateOfInterst = (float) cache.get(city);
		}
		amount = (priciple*time*rateOfInterst)/100;
		return amount;
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		LoanCalculator lcal = new LoanCalculator();
		double calculateIntrst=0.0;
		double calculateIntrst11 = 0.0;
		double calculateIntrst13 = 0.0;
		try {
			calculateIntrst = lcal.calculateIntrst(10000, 3, "JPR");
			
			calculateIntrst11 = lcal.calculateIntrst(10000, 3, "HYD");
			calculateIntrst13 = lcal.calculateIntrst(10000, 3, "HYD");
		} catch (LoanNotApplicableException e) {
			System.out.println("Zone are not match");
			//e.printStackTrace();
		}
		System.out.println("loan intrest amount is "+calculateIntrst);
		System.out.println("loan again "+calculateIntrst11);
		System.out.println("loan again "+calculateIntrst13);
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
 * */
 