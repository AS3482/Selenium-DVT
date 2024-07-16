package test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.commondesignPage;
import pages.loginPage;

public class autosaveLabel_Test41439 {
	private static WebDriver driver = null;
	@BeforeMethod
	public void setUpTest() throws InterruptedException{		

		//initialize the chrome browser	
		WebDriverManager.chromedriver().setup();
		//Headless Browser
		//ChromeOptions option=new ChromeOptions();
		//option.addArguments("window-size=1400,800");
		//option.addArguments("headless");
		driver = new ChromeDriver();	 
		driver.manage().window().maximize();
		driver.get(config.config.url);
		Thread.sleep(2000);
		//login object
		loginPage login = new loginPage(driver);
		login.enterUsername(config.config.username);
		login.enterPassword(config.config.password);
		login.clickLogin();			
		Thread.sleep(2000);

	}
		
		@Test
		public static void autosaveLabel() throws Exception {
			commondesignPage com = new commondesignPage(driver);
			com.clickdriverMessage();
			com.clickCommonDesign();
			Thread.sleep(2000);
			com.clickBarcodes();
			Thread.sleep(2000);
			com.clickBasic();
			Thread.sleep(2000);
			com.clickEdit();
			Thread.sleep(6000);
			com.clickRename();
			Thread.sleep(4000);
			
			
			
		}
		@AfterMethod
		public void tearDown() {
			driver.close();
			driver.quit();
		}

}
