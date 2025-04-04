package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accoungPageSetup() {
		accPage= loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accountsPageTitleTest() {
		String actAccountPageTitle = accPage.getAccountsPagetitle();
		System.out.println(actAccountPageTitle );
		Assert.assertEquals(actAccountPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accountTestHeaderTest() {
		Assert.assertTrue(accPage.accountPageHeaderExist());
	}
	
	@Test
	public void searchExistSearch(){
		Assert.assertTrue(accPage.isSearchExist());		
	}
	
	@Test
	public void accSectionsTest() {
		List <String> actSecList = accPage.getAccountSectionsList();
		System.out.println("Accounts Section List : " + actSecList);
		Assert.assertEquals(actSecList, Constants.ACCOUNT_SECTIONS_LIST);
	}
	
	@Test
	public void searchHeaderTest()
	{
		searchresultPage =accPage.doSearch("MacBook");
		String actSearchHeader= searchresultPage.getResultPageHeaderValue();
		Assert.assertTrue(actSearchHeader.contains("MacBook"));
	}
	
	@Test
	public void searchProductCountTest() {
		searchresultPage =accPage.doSearch("MacBook");
		int actProductCount =searchresultPage.getProductSearchCount();
		Assert.assertEquals(actProductCount, Constants.MACBOOK_PRODUCT_COUNT);
	}
	
	@Test
	public void getSearchProductListTest() {
		searchresultPage =accPage.doSearch("MacBook");
		List<String> actProductList = searchresultPage.getproductResultList();
		System.out.println(actProductList);
	
	}
	
	
	
	
}
