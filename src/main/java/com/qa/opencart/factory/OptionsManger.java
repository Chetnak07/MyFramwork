package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManger {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManger(Properties prop)
	{
		this.prop =prop;
	}
	public ChromeOptions getChromeOptions() {
		co= new ChromeOptions();
		co.addArguments("--remote-allow-origins=*"); 
	      co.addArguments("--start-maximized");
	      
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("---headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("---incognito");
		return co;
	}

	public FirefoxOptions getFirfoxOptions() {
		fo= new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("---headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("---incognito");
		return fo;
	}
	
}
