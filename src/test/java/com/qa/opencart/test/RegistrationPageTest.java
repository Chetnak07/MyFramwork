package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regPageSetup() {
		registrationPage =loginPage.nevigateToRegisterPage();
	}
	
	public String getRendomEmail() {
		Random random= new Random();
		String email ="Fabruary"+ random.nextInt(1000)+"@Gmail.com";
		return email;
	}
	
//	@DataProvider
//	public Object[][] getRegisterData()
//	{
//		return new Object [][]{
//			{"chet","xyx","7898789876","test@123","No"},
//			{"piku","xix","9098789876","test@46","yes"},
//			{"hiku","mike","7898786876","pick@123","No"}
//		};
//	}
	@DataProvider
	public Object[][] getRegisterData(){
		Object regData[][] =ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	@Test(dataProvider= "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telehopne, String password, String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration(firstName,lastName,getRendomEmail(),telehopne,password,subscribe ));
	}
	
	

}
