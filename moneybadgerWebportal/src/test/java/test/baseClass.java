package test;

import static org.testng.Assert.assertEquals;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.loginPage;

public class baseClass {
	static WebDriver driver;

	@BeforeMethod	
	public void setUp() throws Exception{	

		//initialize the chrome browser		
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.http.factory",  "jdk-http-client");
		caps.setBrowserName("chrome");


		URL url = new URL("http://10.48.2.21:4444");
		driver =  new RemoteWebDriver(url, caps);
		driver.manage().window().maximize();			
		
		driver.get(config.config.url);
		Thread.sleep(2000);	 
		//login object
		loginPage login = new loginPage(driver);
		login.enterUsername(config.config.username);
		login.enterPassword(config.config.password);
		login.clickLogin();
		Thread.sleep(2000);
		assertEquals("Zebra Small Office Home Office",driver.getTitle(), "Titles are not matched");

	}	

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
