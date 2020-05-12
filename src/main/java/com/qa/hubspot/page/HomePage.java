package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.cssSelector("h1.private-page__title");
	//By accountName = By.cssSelector("span.account-name");
	By dropDownArrow = By.id("account-menu");
	By accountName = By.xpath("//a[@id='navAccount-current']//div[@class='navAccount-accountName']");
	
	By mainContactsLink =By.id("nav-primary-contacts-branch");
	By childContactsLink= By.id("nav-secondary-contacts");
		
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageHeader()
	{
		elementUtil.waitForElementVisible(header);
		return elementUtil.doGetText(header);
	}
	
	public String getLoggedInUserAccountName()
	{
		elementUtil.waitForElementPresent(dropDownArrow);
		elementUtil.doClick(dropDownArrow);
		return elementUtil.doGetText(accountName);
	}
	
	public String getHomePageTitle(){
		elementUtil.waitForTitlePresent(AppConstants.HOME_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public void clickOnContacts()
	{
		elementUtil.waitForElementPresent(mainContactsLink);
		elementUtil.doClick(mainContactsLink);
		
		elementUtil.waitForElementPresent(childContactsLink);
		elementUtil.doClick(childContactsLink);
	}
	
	public ContactsPage goToContactsPage()
	{
		clickOnContacts();
		return new ContactsPage(driver);
	}
}
