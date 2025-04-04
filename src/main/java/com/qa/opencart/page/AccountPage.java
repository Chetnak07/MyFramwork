package com.qa.opencart.page;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	private By search = By.name("search");
	private By searchbtn = By.cssSelector("div#search button");
	private By header = By.cssSelector("div#logo a");
	private By accSecList = By.cssSelector("div#content h2");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getAccountsPagetitle() {
		return eleutil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
	}

	public boolean accountPageHeaderExist() {
		return eleutil.doIsDisplayed(header);
	}

	public List<String> getAccountSectionsList() {
		List<WebElement> secList = eleutil.getElements(accSecList);
		List<String> accSecValueList = new ArrayList<String>();
		for (WebElement e : secList) {
			String text = e.getText();
			accSecValueList.add(text);
		}
		return accSecValueList;
	}

	public boolean isSearchExist() {
		return eleutil.doIsDisplayed(search);
	}
	
	
	public SearchResultPage doSearch(String productName)
	{
		if (isSearchExist()) {
			eleutil.doSendKeys(search, productName);
			eleutil.doClick(searchbtn);
			return new SearchResultPage(driver);
		}
		return null;
	}

}
