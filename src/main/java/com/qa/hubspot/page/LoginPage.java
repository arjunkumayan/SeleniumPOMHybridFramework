package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.config.Credentials;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class LoginPage extends BasePage{
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	//1. locators - By
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By loginErrorText = By.xpath("//div[@class='private-alert__body has--vertical-spacing']");
	By showPwdText = By.xpath("//span[text()='Show Password']");
	By forgotPwdLinkText = By.xpath("//small[@id='password-description']//*[text()='Forgot my password']");
	
		
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	// page actions
	
	public String getPageTitle()
	{	elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public String isShowPasswordLinkDisplayed()
	{
		elementUtil.waitForElementPresent(showPwdText);
		return elementUtil.doGetText(showPwdText);
		
	}
	
	public String isForgotPasswordLinkDisplayed()
	{
		elementUtil.waitForElementPresent(forgotPwdLinkText);
		return elementUtil.doGetText(forgotPwdLinkText);
	}
	
	public String getPageTitleByJS()
	{
		return jsUtil.getTitleByJS();
	}
	
	public boolean checkSignhUpLink()
	{
		elementUtil.waitForElementPresent(signUpLink);
		return elementUtil.doIsDisplayed(signUpLink);
	}
		
	public HomePage doLogin(Credentials userCred)
	{
		// driver.findElement(this.username).sendKeys(username);
		/*
		 * driver.findElement(emailId).sendKeys(username);
		 * driver.findElement(password).sendKeys(pwd);
		 * driver.findElement(loginButton).click();
		 */
		elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		elementUtil.doSendKeys(emailId, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
	}
	
	
	public boolean checkLoginErrorMesg()
	{
		return elementUtil.doIsDisplayed(loginErrorText);
		
	}
	
	
	

}
