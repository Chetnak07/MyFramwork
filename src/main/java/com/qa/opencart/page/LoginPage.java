package com.qa.opencart.page;

import java.lang.invoke.ConstantCallSite;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Step;

//import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	//private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgetPwd = By.linkText("Forgotten Password");
	private By registrLink = By.linkText("Register");
	private By loginErrorMessg= By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//2. public page constructor
	
	public LoginPage (WebDriver driver) {
		this.driver=driver;
		eleutil= new ElementUtil(driver);
	}
	
	//public page actions
	@Step("getting logonpage title")
	public String getLoginPageTitle(){
		return eleutil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Step("getting logonpage url")
	public String getLoginPageUrl() {
		return eleutil.waitForUrl(Constants.DEFAULT_TIME_OUT,Constants.LOGIN_PAGE_FRACTION_URL);
	}
	
	public boolean isForgetPwdLinkExist() {
		return eleutil.doIsDisplayed(forgetPwd);    
	}
	//Account page is return type
	
	@Step("login to username with{0}and pwd {1}")
	public AccountPage doLogin(String un, String pwd) {
		eleutil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		//next landing page's object
		return new AccountPage(driver);
		
	}

	@Step("login to application with correct username {0} and password {1}")
	public boolean doInvalidLogin(String un, String pwd) {
		WebElement email_ele = eleutil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT);
		email_ele.clear();
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		email_ele.sendKeys(un);
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		eleutil.doSendKeys(password, pwd);
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		eleutil.doClick(loginBtn);
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		String actualErrorMesg = eleutil.doElementGetText(loginErrorMessg);
		System.out.println(actualErrorMesg);
			if(actualErrorMesg.contains(Errors.LOGIN_PAGE_ERROR_MESSG)) {
				return false;
			}
			return true;
	}
	
	public boolean isRegisterLinkExist(){
		return eleutil.waitForElementToBeVisible(registrLink, Constants.DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public RegistrationPage nevigateToRegisterPage() {
		if(isRegisterLinkExist()) {
			eleutil.doClick(registrLink);
			return new RegistrationPage(driver);
		}
		return null;
	}

	
	

}
