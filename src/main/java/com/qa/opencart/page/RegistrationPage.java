package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private static WebDriver driver;

	private static ElementUtil eleutil;
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value = '1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value = '0']");
	private By agreeCheckBox = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	private By successMessage = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
		
	}

	public boolean accountRegistration(String firstname, String lastname, String email, String telephone,
			String password, String subscribe) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eleutil.waitForElementToBeVisible(this.firstname, Constants.DEFAULT_TIME_OUT).sendKeys(firstname);
		eleutil.doSendKeys(this.lastname, lastname);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmpassword, password);
		
		if (subscribe.equalsIgnoreCase("Yes")) {
			eleutil.doClick(subscribeYes);
		} else {
			eleutil.doClick(subscribeNo);
		}
		eleutil.doClick(agreeCheckBox);
		eleutil.doClick(continueBtn);

		if (getAccountRegisterSuccessMessage().contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			goToRegisterPage();
			return true;
		}
		return false;
	}

	public String getAccountRegisterSuccessMessage() {
		return eleutil.waitForElementToBeVisible(successMessage, Constants.DEFAULT_TIME_OUT).getText();
	}

	private void goToRegisterPage() {
		eleutil.doClick(logoutLink);
		eleutil.doClick(registerLink);
	}

}
