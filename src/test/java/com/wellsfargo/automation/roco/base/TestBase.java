package com.wellsfargo.automation.roco.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	public static Properties prop;//Properties - class
	public static WebDriver driver;
	public TestBase() {//we need constructor for inisal config file
		try {
			FileInputStream fls = new FileInputStream(System.getProperty("user.dir")+"src\\test\\java\\com\\bangladesh\\config\\configBD.proterties");
			prop = new Properties();
			prop.load(fls);
		} 		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 		catch (IOException e) {
			e.printStackTrace();
		}		
	}	
	//we need inisal browser
	public void initz() {
		String Browsername = prop.getProperty("browser1");
		if(Browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrom.driver", System.getProperty("user.dir")+"\\bangladesh\\Driver\\chromedriver.exe");	
			driver = new ChromeDriver();
//			driver.manage().timeouts().implicitlyWait(utill.implicitlyWait_wait, TimeUnit.SECONDS);
//			driver.manage().timeouts().implicitlyWait(utill.implicitlyWait_wait, TineUnit.SECONDS);
//			driver.manage().timeouts().pageLoadTimeout(utill.pageLoadTimeout_wait, TimeUnit.SECONDS);
			//Maximize Window
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}		
		else
			System.out.println("Browser not found");		
	}
	//we need a method to launch a browser 
	public static void launchApplication(String URL) {
		driver.get(prop.getProperty("url_QA"));

	}



}