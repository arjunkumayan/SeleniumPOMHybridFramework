package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
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


@Epic("EPIC - 101 : create login page features")
@Feature("US -501:  create test for login page feature")
public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeTest
	@Parameters(value= {"browser"})
	public void setUp(String browser) throws InterruptedException
	{
		String browserName = null;
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		if(browser.equals(null)) {
		   browserName = prop.getProperty("browser");
		} else {
			browserName=browser;
		}
		
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	  	userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1, description="verify login page title test........")
	@Description("verify login page title test........")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() throws InterruptedException
	
	{
		String title = loginPage.getPageTitle();
		System.out.println("login page title is : "+ title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority =2)
	@Description("verify sign up link test.....")
	@Severity(SeverityLevel.CRITICAL)
	public void signUpLinkTest()
	{
		Assert.assertTrue(loginPage.checkSignhUpLink());
		
	}
	
	@Test(priority =3)
	@Description("verify login Test......")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() throws InterruptedException
	{
		homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getLoggedInUserAccountName();	
		Assert.assertEquals(accountName, prop.getProperty("accountName"));
	}
	
	@Test
	public void verifyShowPassowordLinkText()
	{
	
		String passwordLink = loginPage.isShowPasswordLinkDisplayed();
		Assert.assertEquals(passwordLink, AppConstants.LOGIN_SHOW_PASSWORD_LINK_TEXT);
	}
	@Test
	public void verifyForgotPasswordLinkText()
	{
		String ForgotpasswordLink = loginPage.isForgotPasswordLinkDisplayed();
		Assert.assertEquals(ForgotpasswordLink, AppConstants.LOGIN_FORGOT_PASSWORD_LINK_TEXT);
		
	}
	@DataProvider
	public Object[][] getLoginInvalidData()
	{
		Object data[][] = {{"test1@gmail.com","test123"},
				      		{"test2@gmail.com"," "},
				            {" ","test12345"},
				            {" "," "}};
		
		return data;
		
		
	}
	  @Test(dataProvider = "getLoginInvalidData", enabled=false)
	  public void login_InvalidTestCases(String useername, String password) {
		  userCred.setAppUsername(useername);
		  userCred.setAppUsername(password);
		  loginPage.doLogin(userCred);
		  
		  Assert.assertTrue(loginPage.checkLoginErrorMesg());
		  
	  } 
	
    	
	@AfterTest
	public void tearDown()
	{
	   driver.quit();
			
	}

}
