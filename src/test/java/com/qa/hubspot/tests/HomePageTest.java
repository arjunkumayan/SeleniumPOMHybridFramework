package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.config.Credentials;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("EPIC - 102: Create Home Page features")
@Feature("US - 502: Create Home page test")
public class HomePageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	
	@BeforeTest(alwaysRun= true)
	public void setUp() throws InterruptedException
	{
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage =loginPage.doLogin(userCred);
		
	}
	
	@Test(priority=1)
	@Description("verifyhome page title test......")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageTitleTest()
	{		
		String title = homePage.getHomePageTitle();
		System.out.println("Home page title is: "+ title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
		
	}
	@Test(priority=2)
	@Description("verify home page header test......")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageHeaderTest()
	{
		String header = homePage.getHomePageHeader();
		System.out.println("home page header is: "+ header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	@Description("verify logged in user test")
	@Severity(SeverityLevel.CRITICAL)
	public void getLoggedInUserTest()
	{		
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("Logged in account name : "+ accountName);
		Assert.assertEquals(accountName,prop.getProperty("accountName"));
		
	}
		
	@AfterTest(alwaysRun= true)
	public void tearDown()
	{
		driver.quit();
			
	}

}
