package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	Properties prop;
	JavaScriptUtil jsUtil;
	
	
	public ElementUtil(WebDriver driver)
	{   
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIMEOUTS);
		prop = init_properties();
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public boolean waitForElementPresent(By locator)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
		
	}
	
	public boolean waitForElementVisible(By locator)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
		
	}
	
	public boolean waitForTitlePresent(String title)
	{
		wait.until(ExpectedConditions.titleIs(title));
		return true;
		
	}
	
	public String doGetPageTitle()
	{ try {
		return driver.getTitle();
	}
	catch (Exception e) {
		System.out.println("some exception got occureed while getting the title.........");
	}
	return null;
	}
	
	/**
	 * this method is used to create the webelement on the basis of By locator
	 * @param locator
	 * @return
	 */
	
	public WebElement getElement(By locator){
		WebElement element = null;
		try {
			//if(waitForElementPresent(locator));  // depends on your requirement if it working for all test case then good otherwise individually use wait at test case directly
		 element = driver.findElement(locator);
		 if(highlighElement)
		 {
			 jsUtil.flash(element);
		 }
	     //  String highlightProperty = prop.getProperty("highlight");
	     // System.out.println(highlightProperty);
	       //if(highlightProperty.equalsIgnoreCase("yes"))
	        //{
		     // jsUtil.flash(element);
	        //}
		} catch (Exception e) {
			System.out.println("some exception got occureed while creating the web element.........");
		}
		return element;
	}
	
	
	public void doClick(By locator)	{
		try {
		getElement(locator).click();
		}catch (Exception e) {
			System.out.println("some exception got occurred while clicking on the web element.......");
		}
		
	}
	
	public void doSendKeys(By locator, String value) {
		try {
	WebElement element = getElement(locator);
	 element.clear();
	 element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception got occurred while entering value in a field.......");
		}
	}
	
	
	public boolean doIsDisplayed(By locator)
	{
		try {
	        return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some exception got occurred.......");
		}
		return false;
		
	}
	
	public String doGetText(By locator)
	{
		try {
	           return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception got occurred while getting the text from a webelement.......");
		}
		return null;
	}
	
	
	public static void pageScrollToView(WebDriver driver,By locator) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
		
	}
	public void scrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		//js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	
	public static Boolean waitForElementPresent(WebDriver driver,By locator,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
		
	}
	
	
	

}
