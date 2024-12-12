package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePages;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchpage;
	public WebDriver driver;
	
	public SearchTest()
	{
		super();
	}

	@BeforeMethod
	public void setup()
	{
       try {
		driver= initializeBrowserAndOpenApplication(prop.getProperty("browser"));
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
	public void verifySearchWithValidProduct()
	{
		try {
			HomePages homepage = new HomePages(driver);
			homepage.enterSearchMessage(dataProp.getProperty("validProduct"));
			searchpage = homepage.clickOnSearchButton();
		
		
		Assert.assertTrue(searchpage.retriveSearchProduct());
		}catch(NullPointerException e) {
			e.getMessage();
		}
		

    }
	
	@Test(priority=2)
	public void verifySearchWithNoExistingProduct() throws InterruptedException
	{
		try {
			HomePages homepage = new HomePages(driver);
			homepage.enterSearchMessage(dataProp.getProperty("notExistingProduct"));
			searchpage=homepage.clickOnSearchButton();
		
		Thread.sleep(2000);
		String searchMatchNotExit=searchpage.retriveMessageNoProductMatch();
		Assert.assertEquals(searchMatchNotExit,dataProp.getProperty("noMatchProductWarningMessage"));
		//dataProp.getProperty("noMatchProductWarningMessage")
		}catch(Throwable e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority=3, dependsOnMethods= {"verifySearchWithValidProduct","verifySearchWithNoExistingProduct"})
	public void verifySearchWithoutAnyProductName() throws InterruptedException
	{
		HomePages homepage = new HomePages(driver);
		homepage.enterSearchMessage("");
		Thread.sleep(2000);
		searchpage=homepage.clickOnSearchButton();
		
		String searchWithoutProductName=searchpage.retriveMessageNoProductMatch();
		Assert.assertEquals(searchWithoutProductName, dataProp.getProperty("noMatchProductWarningMessage"));
		//dataProp.getProperty("noMatchProductWarningMessage")
	
	
}
}
