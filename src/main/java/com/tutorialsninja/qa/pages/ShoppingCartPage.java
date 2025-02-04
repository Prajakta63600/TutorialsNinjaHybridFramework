package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
	
	WebDriver driver;

	@FindBy(xpath="//a[normalize-space()='Continue Shopping']")
	WebElement ContinueShoppingButton;
	
	public ShoppingCartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnContinueShoppingButton()
	{
		ContinueShoppingButton.click();
	}
}
