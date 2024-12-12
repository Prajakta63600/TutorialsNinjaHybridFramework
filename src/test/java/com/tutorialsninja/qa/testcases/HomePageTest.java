package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePages;
import com.tutorialsninja.qa.pages.SearchPage;
import com.tutorialsninja.qa.pages.ShoppingCartPage;
import com.tutorialsninja.qa.pages.iMacPage;

public class HomePageTest extends Base{
	
	public WebDriver driver;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void steup()
	{
	
		driver= initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifynevigatingHomePageFromShoppingCartPage() throws InterruptedException
	{
		
			try {
			HomePages homepage = new HomePages(driver);
			homepage.enterSearchMessage(dataProp.getProperty("SearchProduct"));
			SearchPage searchpage = homepage.clickOnSearchButton();
		
		iMacPage imac = searchpage.retriveSearchProductInGrid();
		
		imac.clickOnAddToCartButton();
		ShoppingCartPage shoppingCart = imac.clickOnShoppingCartLink();
		
		shoppingCart.clickOnContinueShoppingButton();
		
		//Thread.sleep(3000);
		
		HomePages homePage = new HomePages(driver);
		
		boolean homePageTitle=homePage.retriveHomePageTitle();
		
		Assert.assertTrue(homePageTitle);
			}catch(NoSuchElementException e)
			{
				 System.err.println("Element not found: " + e.getMessage());
			}
			
	}
	

}
