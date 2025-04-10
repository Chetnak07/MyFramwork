package com.qa.opencart.page;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("div#content img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By successMessg = By.cssSelector("div.alert.alert-success.alert-dismissible");
	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getProdctHeader() {
		return eleUtil.doElementGetText(productHeader).trim();
	}


	public int getProductImageCount() {
		return eleUtil.waitForElementsToBeVisible(productImages, Constants.DEFAULT_TIME_OUT).size();
	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("productName", getProdctHeader());
		productInfoMap.put("imageCount", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);

		for (WebElement e : metaDataList) {
			String text = e.getText().trim();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);

		}
	}

	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
		String price =metaPriceList.get(0).getText().trim();
		String exPrice =metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exPrice", exPrice);

		

		}
	

}
