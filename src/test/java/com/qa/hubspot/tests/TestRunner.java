package com.qa.hubspot.tests;

import org.testng.TestNG;

import com.qa.hubspot.listeners.ExtentReportListener;

public class TestRunner {

	static TestNG testNG;
	
	public static void main(String[] args) {
		
		ExtentReportListener extent = new ExtentReportListener();
		testNG= new TestNG();
		
		testNG.setTestClasses(new Class[] {LoginPageTest.class});

		 testNG.addListener(extent);
		 
		 testNG.run();
	}

}
