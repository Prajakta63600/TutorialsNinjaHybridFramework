package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	
	public WebDriver driver;
	
	//Objects
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement firstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement lastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailAddress;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement telephoneNumber;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement confirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreebutton;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continuebutton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement subscribe;
	
    @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailExistMessage;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement PrivecyPolicy;
	
	@FindBy(xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement PrivecyPolicyFisrtName;
	
	@FindBy(xpath="//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement PrivecyPolicyLastName;
	
	@FindBy(xpath="//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement PrivecyPolicyEmail;
	
	@FindBy(xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement PrivecyPolicyTelephone;
	
	@FindBy(xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement PrivecyPolicyPassword;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void enterFirstName(String Fname)
	{
		firstName.sendKeys(Fname);
	}
	
	public void enterLastName(String Lname)
	{
		lastName.sendKeys(Lname);
	}
	
	public void enterEmail(String Email)
	{
		emailAddress.sendKeys(Email);
	}
	
	public void enterTelephoneNumber(String telephone)
	{
		telephoneNumber.sendKeys(telephone);
	}
	
	public void enterPassword(String Password)
	{
		password.sendKeys(Password);
	}
	
	public void enterConfirmPassword(String CPassword)
	{
		confirmPassword.sendKeys(CPassword);
	}
	
	public void clickPrivecyPolicy()
	{
		agreebutton.click();
	}
	
	public AccountSuccessPage clickOnContinueButton()
	{
		continuebutton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectSubscriberYes()
	{
		subscribe.click();
	}
	
	public String checkEmailExistWarrningMessage()
	{
		String emailExistMessageText=emailExistMessage.getText();
		return emailExistMessageText;
	}
	
	public String retrivePrivecyPolicy()
	{
		String PrivecyPolicyText=PrivecyPolicy.getText();
		return PrivecyPolicyText;
	}
	
	public String retrivePrivecyPolicyFirstName()
	{
		String WarnningFirstNameText=PrivecyPolicyFisrtName.getText();
		return WarnningFirstNameText;
	}
	
	public String retrivePrivecyPolicyLastName()
	{
		String WarnningLastName=PrivecyPolicyLastName.getText();
		return WarnningLastName;
	}
	
	public String retrivePrivecyPolicyEmail()
	{
		String WarnningEmailText=PrivecyPolicyEmail.getText();
		return WarnningEmailText;
	}
	
	public String retrivePrivecyPolicyTelephone()
	{
		String WarnningTelephoneText=PrivecyPolicyTelephone.getText();
		return WarnningTelephoneText;
	}
	
	public String retrivePrivecyPolicyPassword()
	{
		String WarnningPasswordText=PrivecyPolicyPassword.getText();
		return WarnningPasswordText;
	}

}
