package com.wellsfargo.automation.roco.base;

import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.IOException;
import java.lang.module.Configuration;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;
//import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RocoTestBase  {
	public static WebDriver driver;

	public static int SMALL_WAIT = 0;
	public static int MEDIUM_WAIT =0;
	public static int LONG_WAIT = 0;
	public static int VERY_LONG_WAIT = 0;
	public static int iSeleniumWaitTime = 0;

	private String nasaBaseURL = null;

	public RocoTestBase() {
		iSeleniumWaitTime = NumberUtils.toInt(ConfigurationManager.getBundle().getString("selenium.wait.timeout"));
		SMALL_WAIT = iSeleniumWaitTime;
		MEDIUM_WAIT = iSeleniumWaitTime * 3;
		LONG_WAIT = iSeleniumWaitTime * 6;
		VERY_LONG_WAIT = iSeleniumWaitTime * 10;
	}
	public void invoke() {
		launchPage(null);
	}
	public void waitForPageToLoad() {
		super.waitForPageToLoad();
	}
	public void openPage(PageLocator locator, Object... args) {

	}
	@Test
	public void openBrowser() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	public void openURL() throws Exception {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.manage().deleteAllCookies();
	}
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	public void captureScreenShot(String fileName ) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+ fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");

	}
	public String todaysDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		return dtf.format(localDate);		
	}
	public String nextDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now().plusDays(1);
		return dtf.format(localDate);		
	}
	public String yesterDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now().minusDays(1);
		return dtf.format(localDate);		
	}
	public void checkACheckBox(WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}
	}
	public void unCheckACheckBox(WebElement element) {
		if(element.isSelected()) {
			element.click();
		}
	}
	public boolean verifyAllCheckBoxesAreChecked() {
		List <WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='recordId']"));
		for(int i=0; i<checkboxes.size(); i++) {
			WebElement element = checkboxes.get(i);		
			if(!element.isSelected()) {
				Reporter.log("Expected result not found", MessageTypes.Fail);
				return false;
			}
		}
		Reporter.log("Expected result found", MessageTypes.Pass);
		return true;
	}
	public boolean verifyAllCheckBoxesAreUnChecked() {
		List <WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='recordId']"));
		for(int i=0; i<checkboxes.size(); i++) {
			WebElement element = checkboxes.get(i);		
			if(element.isSelected()) {
				Reporter.log("Expected result not found", MessageTypes.Fail);
				return false;
			}
		}
		Reporter.log("Expected result found", MessageTypes.Pass);
		return true;
	}

	public WebDriver getDriver() {
		return getTestBase().getDriver();
	}
	public QAFExtendedWebDriver getDriver() {
		return getTestBase().getDriver();
	}
	public void verifyPageTitle(String exectedPageTitle) {
		try {
			String acutalTitle = getTestBase().getDriver().getTitle();
			Reporter.log("Expected Title: "+ exectedPageTitle+" -- " +"Acutal Received: "+acutalTitle );
		}
		catch (Exception e) {
			Reporter.LogWithScreenShot("Failed to get the page title. Exception:"+ e.getMessage(), MessageTypes.Fail);
		}
	}
	public String getPageTitle() {
		String actualTitle = null;
		try {
			waitForPageToLoad();
			actualTitle = getTestBase().getDriver().getTitle();
			Reporter.log("Actual Title: " + actualTitle);
		}
		catch(Exception e) {
			Reporter.LogWithScreenShot("Failed to get the page title. Exception:"+ e.getMessage(), MessageTypes.Fail);
		}
		return actualTitle;
	}
	public class OpenPage {
		public String PAGE_URL ="http://www.AUT.com";
		public String PAGE_TITLE = "Welcome!";
		public WebDriver driver;
	}
	public OpenPage (WebDriver driver){
		this.driver = driver;
	}
	public void loadPage (){
		driver.get(PAGE_URL);

		driver.manage().window().maximize();
		driver.manage().window().getSize();
		driver.manage().window().fullscreen ();
		driver.manage().window().open();
	}
}

