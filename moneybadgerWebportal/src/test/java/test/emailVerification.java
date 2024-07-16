package test;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.genericMethods;

public class emailVerification{
	String email_prefix = "dvt";
	static WebDriver driver;
	public static WebDriver driver_chrome;
	@Test
	public void mailTesting() throws InterruptedException, IOException {
		URL url = null;
		url = new URL("http://10.80.4.101:4444");
		
		//desired capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.http.factory",  "jdk-http-client");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
	
		//options.setCapability(EdgeOptions.CAPABILITY,options);
		//caps.setCapability(ChromeOptions.CAPABILITY,options);			
		caps.setBrowserName("chrome");	
		caps.setCapability(ChromeOptions.CAPABILITY,options);
		caps.setPlatform(Platform.WINDOWS);

		//URL url = new URL("http://10.80.4.108:5555");
		driver =  new RemoteWebDriver(url, caps);
		//driver.manage().deleteAllCookies();
		//Thread.sleep(7000);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		Reporter.log("Browser is set chrome");
		driver_chrome=driver;
		
		genericMethods zc = new genericMethods(driver);
		//z.setBrowser("chrome");
		//genericMethods zc = new genericMethods(driver_chrome);	
		
		String randomString = RandomStringUtils.randomAlphabetic(1) +
		RandomStringUtils.random(1, "aeiou") +
		RandomStringUtils.randomAlphabetic(3) +
		RandomStringUtils.randomAlphabetic(1) +
		RandomStringUtils.random(1, "aeiou") +
		RandomStringUtils.randomAlphabetic(3);
		email_prefix =email_prefix + randomString.toLowerCase();
		System.out.println("This is the email "+email_prefix);
		
		//Registration
		//zc.randomEmail("@code");
		zc.getURL("https://zsbportal-stage.zebra.com/");
		Thread.sleep(1000);
		zc.Click("xpath", "//button[@class='email']");
		//driver.findElement(By.xpath("//button[@class='email']")).click();
		Thread.sleep(2000);
		zc.Click("xpath", "//a[normalize-space()='Register Your Email Now']");
		//driver.findElement(By.xpath("//a[normalize-space()='Register Your Email Now']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
		Thread.sleep(1000);	
		zc.sendText("xpath", "//input[@type='email']", email_prefix+"@mail7.io");		
		zc.Click("xpath", "//input[@id='submit']");
		//driver.findElement(By.xpath("//input[@id='submit']")).click();
//		Thread.sleep(3000);
//		zc.refresh();
//		zc.sendText("xpath", "//input[@type='email']", email_prefix+"@mail7.io");
//		zc.Click("xpath", "//input[@id='submit']");
		Thread.sleep(8000);

		
		//z.verificationCode("@code");
		
//		//getting verification code
		String mainWindowHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);		
		driver.get("https://www.mail7.io/");
		Thread.sleep(1000);
		zc.scrollToElement("xpath", "(//*[@name='username'])[2]");
		driver.findElement(By.xpath("(//*[@name='username'])[2]")).sendKeys(email_prefix);
		Thread.sleep(4000);		
		driver.findElement(By.xpath("(//*[@type='submit'])[2]")).click();		
		Thread.sleep(9000);
		driver.findElement(By.xpath("//b[normalize-space()='Zebra Service Do Not Reply']")).click();		
		Thread.sleep(6000);		
		WebElement frEle=driver.findElement(By.xpath("//*[@class='message']/iframe"));
		driver.switchTo().frame(frEle);
		WebElement ele = driver.findElement(By.xpath("//font/b"));		
		Thread.sleep(2000);
		String verCode = ele.getText();
		System.out.println("This is the verification code "+verCode);		
		driver.close();
		
		//enter verification code into zsb
		driver.switchTo().window(mainWindowHandle);
		driver.findElement(By.xpath("//input[@class='otp-input']")).sendKeys(verCode);
		
		zc.Click("xpath", "//input[@class='next-otp action-button']");
		//driver.findElement(By.xpath("//input[@class='next-otp action-button']")).click();
		Thread.sleep(2000);
		
		zc.closeBrowser();

}
}
