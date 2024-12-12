package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;

	@FindBy(xpath="//div[@class='product-thumb']")
	private WebElement searchProduct;
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement nonExistSearchedProduct;
	
	@FindBy(xpath="//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']")
	private WebElement SearchedProductInGrid;
	
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean retriveSearchProduct()
	{
		boolean searchProductDislay=searchProduct.isDisplayed();
		return searchProductDislay;
	}
	
	public String retriveMessageNoProductMatch()
	{
		String nonExitSeacrhProductText=nonExistSearchedProduct.getText();
		return nonExitSeacrhProductText;
	}
	
	public iMacPage retriveSearchProductInGrid()
	{
		SearchedProductInGrid.click();
		return new iMacPage(driver);
	}

	

	
}
