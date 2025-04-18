package com.qa.opencart.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	private By searchHeader = By.cssSelector("div#content h1");
	private By products = By.cssSelector("div.caption a");
	
	
	public  SearchResultPage(WebDriver driver) {
		this.driver= driver;
		eleutil = new ElementUtil(driver);
	}
	
	public String getResultPageHeaderValue() {
		if (eleutil.doIsDisplayed(searchHeader)) {
			return eleutil.doElementGetText(searchHeader);
		}
		return null;
	}
	
	public int getProductSearchCount() {
		return eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT).size();
	}
	
	public List<String> getproductResultList() {
		List<WebElement> productList =eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT);
		List<String> productValList = new ArrayList<String>();
		for(WebElement e: productList) {
			String text= e.getText();
			productValList.add(text);
		}
		return productValList;
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("main product name :" + mainProductName);
	List<WebElement> productList = eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT);
	for(WebElement e: productList) {
		String text = e.getText();
		if(text.equals(mainProductName)) {
			e.click();
			break;
		}
		
	}
	return new ProductInfoPage(driver);
		
	}

}
