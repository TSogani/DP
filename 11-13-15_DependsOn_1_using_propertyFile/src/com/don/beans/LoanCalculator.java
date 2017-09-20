package com.don.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

public class LoanCalculator {
	public double calculateIntrst(long priciple,int time, String city) throws FileNotFoundException, IOException{
		
		float rateOfInterst=0.0f;
		double amount = 0.0;
		// intrest rate depends on city 
		// or all intrest rate specify in the one propertyfile 
		Properties cityIntrestProp = null; 
		
		// read the data from properties
		cityIntrestProp = new Properties();
		// load into properties collection
		cityIntrestProp.load(this.getClass().getClassLoader().getResourceAsStream("city_ri.properties"));
		
		Enumeration<?> propertyNames = cityIntrestProp.propertyNames();
		
		while (propertyNames.hasMoreElements()) {
			Object object = (Object) propertyNames.nextElement();
			System.out.println(object);
		}
		
		if(cityIntrestProp.containsKey(city)){
			rateOfInterst = Float.parseFloat(cityIntrestProp.getProperty(city));
			System.out.println("rate of intrest for : "+city+" : is :  "+rateOfInterst);
		}
		else{
			System.out.println("city not applicable for loan");
		}
		amount = (priciple*time*rateOfInterst)/100;
		return amount;
	}
	
}

// problem with this code read the sa,e data  repetaedly  fot every call to the caller mathod 
// so we go for cache 
// we check in cache data available or not in cache if not available then read from  property file if available in property collection then ot data with key and value store in cache also. 

