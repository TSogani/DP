package com.don.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.don.beans.LoanCalculator;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext("com/don/common/applicationContext.xml");
		LoanCalculator calculator = context.getBean("loanCalculator",LoanCalculator.class);
		double calculateIntrst = calculator.calculateIntrst(10000, 3, "JPR");
		System.out.println(calculateIntrst);
	}

}
