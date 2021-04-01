package com.automationpractice.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.automationpractice.util.TestUtil;

public class BaseClass {
	
	static WebDriver driver; 
	static Properties prop;
	public BaseClass() {
		try 
		{
			prop =new Properties();
			FileInputStream input = new FileInputStream(
			System.getProperty("user.dir") + "/src/main/java/com/automationpractice/config/config.properties");
			prop.load(input);
		}
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		}catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		String browsername = prop.getProperty("browser");
		
		if(browsername.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pavilion.hp\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		driver = new ChromeDriver();
		}else if(browsername.equals("Firefox")) {
			System.setProperty("webdriver.geko.driver", "");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}

}
