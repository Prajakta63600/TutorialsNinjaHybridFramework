package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class iMacPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement addToCardButton;
	
	@FindBy(xpath="//a[normalize-space()='shopping cart']")
	WebElement ShoppingCartLink;
	
	public iMacPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnAddToCartButton()
	{
		addToCardButton.click();
	}
	
	public ShoppingCartPage clickOnShoppingCartLink()
	{
		ShoppingCartLink.click();
		return new ShoppingCartPage(driver);
	}

}
