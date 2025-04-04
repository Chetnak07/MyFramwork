package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


import com.qa.opencart.utils.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManger optionsManger;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static final Logger log = Logger.getLogger(DriverFactory.class);
	
	/**
	 * This method used to initialize WebDriver on the basis of given browse name
	 * this method take care all local and remote executions
	 * @param browserName
	 * @return
	 */

	public WebDriver init_driver(Properties prop) {
		String browserName =prop.getProperty("browser").trim();
		System.out.println("Browser name is : " + browserName);
		log.info("Browser name is : " + browserName);
		highlight =prop.getProperty("highlight");
		optionsManger = new OptionsManger(prop);

		if (browserName.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE)) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManger.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManger.getChromeOptions()));
		} 
		else if (browserName.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VALUE)) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManger.getFirfoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManger.getFirfoxOptions()));
		}
		else if (browserName.equalsIgnoreCase(Browser.SAFARI_BROWSER_VALUE	)) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		} 
		else {
			System.out.println("Please pass the righ browser" + browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	/**
	 * thid will return the thread local copy of the webdriver (driver)
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
		}
	
	/**
	 * this method is used to initialize the properties on the basis on given environment:
	 * environment: QA/DEV/Stage/Prod
	 * @return this return prop
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName = System.getProperty("env");
		System.out.println("Running tests on environment: " + envName);

		if (envName == null) {
			System.out.println("No env is given....hence running it on QA");
			try {
				ip = new FileInputStream("./src/test/resourcess/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resourcess/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resourcess/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resourcess/config/dev.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resourcess/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resourcess/config/config.properties");
					break;

				default:
					System.out.println("please pass the right environment....." + envName);
					break;
				}
			} catch (Exception e) {

			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	
	/**
	 * 
	 */
	public static String getScreenshot() {
	File srcFile =	((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir")+ "/Screenshot/" + System.currentTimeMillis()+ ".png";
	
	File destination = new File (path);
	
	try {
		FileUtils.copyFile(srcFile, destination);
	} catch (IOException e) { 
		e.printStackTrace();
	}
	return path;
	}
	
}
