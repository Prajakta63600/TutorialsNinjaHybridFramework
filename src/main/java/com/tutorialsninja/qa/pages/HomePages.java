package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePages {
	
	WebDriver driver;
	
	//objects
	@FindBy(xpath="//span[normalize-space()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	private WebElement loginOption;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	private WebElement registerPage;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement searchBox;
	
	@FindBy(xpath="//i[@class='fa fa-search']")
	private WebElement clickSearchButton;
	
	@FindBy(xpath="//a[normalize-space()='Qafox.com']")
	private WebElement homePageTitle;
	
	public HomePages(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		}
	
	//Actions
	
	public void clickOnMyAccount()
	{
		myAccountDropMenu.click();
	}
	
	public LoginPages clickOnLogin()
	{
		loginOption.click();
		return new LoginPages(driver);
	}
	
	public RegisterPage clickOnRegister()
	{
		registerPage.click();
		return new RegisterPage(driver);
	}
	
	public void enterSearchMessage(String searchProduct)
	{
		searchBox.sendKeys(searchProduct);
	}
	
	public SearchPage clickOnSearchButton()
	{
		clickSearchButton.click();
		return new SearchPage(driver);
	}
	
	public boolean retriveHomePageTitle()
	{
		boolean homePageTitleText=homePageTitle.isDisplayed();
		return homePageTitleText;
	}

}
