package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locators or ObectRepository - OR
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	
	private By registerLink = By.linkText("Register");
	
	//page constructor
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//page actions/methods
	@Step("Getting login page title")
	public String getLoginPageTitle()
	{
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page title:" +title);
		return title;		
	}
	
	
	@Step("Getting login page url")
	public String getLoginPageURL()
	{
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page url:" +url);
		return url;		
	}
	
	
	@Step("Checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	
	@Step("Checking logo exist")
	public boolean isLogoExist()
	{
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	
	@Step("username is: {0} and password {1}")
	public AccountsPage doLogin(String username, String pwd)
	{
		System.out.println("Credentials are: " + username + " : " + pwd);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);		
	}
	
	
	@Step("Navigating to register page")
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		
		return new RegisterPage(driver);
	}
	
}
