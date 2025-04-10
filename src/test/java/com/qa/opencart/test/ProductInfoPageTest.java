package com.qa.opencart.test;

import java.util.Map;

import javax.swing.text.AbstractDocument.Content;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.page.AccountPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class ProductInfoPageTest extends BaseTest {
  
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
//	@DataProvider
//     public Object[][] getProductData() {
//		Object producrData [][]= ExcelUtils.getTestData(Constants.PRODUCT_SHEET_NAME);
//		return producrData;
//	}
	
	@Test(dataProvider = "getProductData")
	public void productInfoHeaderTest(String productName, String mainProductName)
	{
		searchresultPage = accPage.doSearch(productName);
		productInfoPage =searchresultPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProdctHeader(), mainProductName) ;
	}
	
	@Test 
	public void productImageTest() {
		searchresultPage = accPage.doSearch("Mac Book");
		productInfoPage =searchresultPage.selectProduct("Mac BookPro");
		Assert.assertTrue(productInfoPage.getProductImageCount() == Constants.MACBOOK_IMAGE_COUNT) ;
	}
	
	@Test(dataProvider = "getProductData")
	public void getProductInfoTest(String productName, String mainProductName) {
		searchresultPage = accPage.doSearch(productName);
		productInfoPage =searchresultPage.selectProduct(mainProductName);
	   Map<String,String> actProductInforMap =	productInfoPage.getProductInfo();
	   actProductInforMap.forEach((k,v) -> System.out.println(k+ ":" +v));
	   
	  softAssert.assertEquals(actProductInforMap.get("productName"),"MacBook Air");
	  softAssert.assertEquals(actProductInforMap.get("Brand"),"Apple");
	  softAssert.assertEquals(actProductInforMap.get("Reward Points"),"800");
	  softAssert.assertEquals(actProductInforMap.get("price"),"$2,000.00");
	  softAssert.assertEquals(actProductInforMap.get("Product Code"),"Product 18");
	  softAssert.assertAll();
		
	}
}
