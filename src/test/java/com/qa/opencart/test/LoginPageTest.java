package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 - Design login page for open cart application")
@Story("US 101 - desgin login page features")
public class LoginPageTest extends BaseTest {
	@Test
	
	@Description("login Page Title Test.....")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("The page Title : " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Test
	@Description ("login page url test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		System.out.println("login page url : " + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}

	@Test

	public void forgetPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgetPwdLinkExist());
	}

	@Test
	@Description ("login page  test")
	@Severity(SeverityLevel.CRITICAL)
	public void loginTest() {
		accPage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.accountPageHeaderExist());
	}

	@Test
	public void isRegisterLinkExist() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}


}
