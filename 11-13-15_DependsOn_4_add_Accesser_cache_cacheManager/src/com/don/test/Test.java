package com.don.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.don.beans.LoanCalculator;
import com.don.beans.LoanNotApplicableException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

//		ApplicationContext context = new ClassPathXmlApplicationContext(
		//		"com/don/common/applicationContext.xml");
//		LoanCalculator lcal = context.getBean("loanCalculator",
		// 		LoanCalculator.class);
				LoanCalculator lcal = new LoanCalculator();

		double calculateIntrst = 0.0;
		double calculateIntrst11 = 0.0;
		double calculateIntrst13 = 0.0;
		try {
			calculateIntrst = lcal.calculateIntrst(10000, 3, "JPR");
			System.out.println("loan intrest amount is " + calculateIntrst);
			
			calculateIntrst13 = lcal.calculateIntrst(10000, 3, "JPR");
			System.out.println("loan again 13 : " + calculateIntrst13); 
			
			
			calculateIntrst11 = lcal.calculateIntrst(10000, 3, "HaYD");
			System.out.println("loan again 11 : " + calculateIntrst11);
			
		} catch (LoanNotApplicableException e) {
			System.out.println("Zone are not match");
			// e.printStackTrace();
		}



	}

}
