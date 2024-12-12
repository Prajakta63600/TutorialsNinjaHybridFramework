package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPages {
	
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//input[@id='input-email']")
   WebElement emailAddressField;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement emailPasswordNotMatchingWarrning;
	
	public LoginPages(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public AccountPage clickLoginButton()
	{
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retriveEmailPasswordNotMatching()
	{
		String warningText=emailPasswordNotMatchingWarrning.getText();
		return warningText;
	}
	
	

}
