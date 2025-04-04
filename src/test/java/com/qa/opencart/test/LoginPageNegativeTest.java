package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageNegativeTest extends BaseTest {
	
	@DataProvider
	public Object[][] getLoginNegativeData() {
		return new Object[][] {
			
			{"cure1238@gmail.com", "34567"},
			{"test23450@gmail.com", "Mod2312"}
		};
	}
	
	
	@Test(dataProvider = "getLoginNegativeData")
	@Description("login Title Test with invalid credentials.....")
	@Severity(SeverityLevel.NORMAL)
	public void loginInvalidTest(String un, String pwd) {
		Assert.assertTrue(loginPage.doInvalidLogin(un, pwd), Errors.LOGIN_PAGE_ERROR_MESSG_NOT_DISPLAYED);
	}
	
}
