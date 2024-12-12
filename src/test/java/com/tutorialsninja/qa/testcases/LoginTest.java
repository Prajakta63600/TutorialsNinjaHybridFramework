package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePages;
import com.tutorialsninja.qa.pages.LoginPages;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base{
	

	public WebDriver driver;
	LoginPages loginpages;
	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException {
	try {
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePages homePages = new HomePages(driver);
		homePages.clickOnMyAccount();
		Thread.sleep(1000);
	    loginpages = homePages.clickOnLogin();
	
	}catch(TimeoutException e) {
	System.out.println(e.getMessage());
	}
	}
	
	
	
	@Test(priority=1, dataProvider="validCredialsSupplier")
	public void verifyLoginWithValidCreditails(String email, String password) throws InterruptedException
	{
		
		loginpages.enterEmailAddress(email);
		loginpages.enterPassword(password);
		Thread.sleep(2000);
		AccountPage accountpage = loginpages.clickLoginButton();
		
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformation());
		
	}
	
	@DataProvider(name="validCredialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCreditails() throws InterruptedException
	{ 
		
		loginpages.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginpages.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpages.clickLoginButton();
		
		
		Thread.sleep(3000);
		String actualWraningMessage=loginpages.retriveEmailPasswordNotMatching();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatch");
		Assert.assertTrue(actualWraningMessage.contains(expectedWarningMessage), "Message is not displaying");	
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() throws InterruptedException
	{ 
		
		loginpages.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginpages.enterPassword(prop.getProperty("password"));
		loginpages.clickLoginButton();
		
		Thread.sleep(3000);
		String actualWraningMessage=loginpages.retriveEmailPasswordNotMatching();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatch");
		Assert.assertTrue(actualWraningMessage.contains(expectedWarningMessage), "Message is not displaying");	
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmialAndInvalidPassword() throws InterruptedException
	{ 
		
		
		loginpages.enterEmailAddress(prop.getProperty("email"));
		loginpages.enterPassword(dataProp.getProperty("invalidpassword"));
		loginpages.clickLoginButton();
		
		Thread.sleep(3000);
		String actualWraningMessage=loginpages.retriveEmailPasswordNotMatching();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatch");
		Assert.assertTrue(actualWraningMessage.contains(expectedWarningMessage), "Message is not displaying");		
	}
	
	@Test(priority=5)
	public void verifyWithoutLoginCreditals() throws InterruptedException
	{ 
		
		loginpages.clickLoginButton();
		
		Thread.sleep(2000);
		String actualWraningMessage=loginpages.retriveEmailPasswordNotMatching();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatch");
		Assert.assertTrue(actualWraningMessage.contains(expectedWarningMessage), "Message is not displaying");	
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null) { 
		driver.quit();
		}
		 
		
	}
		
}
