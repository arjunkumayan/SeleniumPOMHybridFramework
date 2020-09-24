package com.qa.hubspot.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testhh {
	
	public static void getSelectDropDownValue(String value) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		WebElement ele =driver.findElement(By.xpath("//div[@id='mCSB_4_container']/div/span[contains(text(),'"+value+"')]//parent::div//span[@class='chk-ele']"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		
		
		//WebElement ele =driver.findElement(By.xpath("//div[@id='mCSB_4_container']/div/span[contains(text(),'"+value+"')]//parent::div//span[@class='chk-ele']"));
		ele.click();
		
		//div[@id='mCSB_4_container']/div/span[contains(text(),'Armenia')]//parent::div//span[@class='chk-ele']
		
		
		
	}

	public static void main(String[] args) {
		
		
		
		
		
		//div[@id='mCSB_4_container']/div/span[contains(text(),'Armenia')]//parent::div//span[@class='chk-ele']

	}

}
