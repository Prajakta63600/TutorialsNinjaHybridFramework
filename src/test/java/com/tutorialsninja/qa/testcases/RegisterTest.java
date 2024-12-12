package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePages;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage successPage;
	
	public RegisterTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		try {
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePages homepage = new HomePages(driver);
		homepage.clickOnMyAccount();
		registerPage = homepage.clickOnRegister();
		}catch(TimeoutException e)
		{
			 System.out.println("Timeout occurred while waiting for the element: " + e.getMessage());
		}
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null) {
		driver.quit();
		}
	}
	
	@Test(priority=1)
	public void verifyRegisteringaccountWithMandatoryField()
	{
		try{
			registerPage.enterFirstName(dataProp.getProperty("firstName"));
			registerPage.enterLastName(dataProp.getProperty("lastName"));
			registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
			registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
			registerPage.enterPassword(prop.getProperty("password"));
			registerPage.enterConfirmPassword(prop.getProperty("password"));
			registerPage.clickPrivecyPolicy();
			successPage = registerPage.clickOnContinueButton();
			
		String actulSuccessMessage=successPage.retirveAccountSuccessPageHeading();
		
		Assert.assertEquals(actulSuccessMessage, dataProp.getProperty("accountCreatedMessage"), "Account Success Message if not displaying");
		}
		catch(NullPointerException e){
			  e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() throws InterruptedException
	{
		 
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("password"));
		registerPage.enterConfirmPassword(prop.getProperty("password"));
		registerPage.selectSubscriberYes();
		registerPage.clickPrivecyPolicy();
		successPage=registerPage.clickOnContinueButton();
		Thread.sleep(2000);
		
		String actulSuccessMessage=successPage.retirveAccountSuccessPageHeading();
		
		Assert.assertTrue(actulSuccessMessage.contains(dataProp.getProperty("accountCreatedMessage")));
		
	}
	
	@Test(priority=3)
	public void verifyRegistringAccountWithExistingEmail()
	{ 
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("naikprajakta124@gmail.com");
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("password"));
		registerPage.enterConfirmPassword(prop.getProperty("password"));
		registerPage.selectSubscriberYes();
		registerPage.clickPrivecyPolicy();
		registerPage.clickOnContinueButton();
		
		String actulSuccessMessage=registerPage.checkEmailExistWarrningMessage();
		
		Assert.assertTrue(actulSuccessMessage.contains(dataProp.getProperty("duplicateWarningMessage")));
			
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{
		registerPage.clickOnContinueButton();
		
		String actualPrivacyPolicyMessage = registerPage.retrivePrivecyPolicy();
		Assert.assertTrue(actualPrivacyPolicyMessage.contains(dataProp.getProperty("privacyPolicyWarning")), "Warring Message is not displaying");
		
		String actualPrivacyPolicyFirstName= registerPage.retrivePrivecyPolicyFirstName();
		Assert.assertTrue(actualPrivacyPolicyFirstName.contains(dataProp.getProperty("firstNameWarning")), "Warring message for first name is not displed");
		
		String actualLastName = registerPage.retrivePrivecyPolicyLastName();
		Assert.assertTrue(actualLastName.contains(dataProp.getProperty("lastNameWarning")), "Warring message for Last name not displayed");
		
		String actualEmail = registerPage.retrivePrivecyPolicyEmail();
		Assert.assertTrue(actualEmail.contains(dataProp.getProperty("emailWarning")), "Warring message for email not displayed");
		
		String actualTelephone = registerPage.retrivePrivecyPolicyTelephone();
		Assert.assertTrue(actualTelephone.contains(dataProp.getProperty("telephoneWarning")), "Warring message for telephone not displayed");
		
		String actualPassword = registerPage.retrivePrivecyPolicyPassword();
		Assert.assertTrue(actualPassword.contains(dataProp.getProperty("passwordWarning")), "Warring message for password not displayed");
			
	}

}
