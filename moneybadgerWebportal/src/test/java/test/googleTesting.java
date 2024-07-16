package test;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import groovy.util.ScriptException;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.genericMethods;

public class googleTesting {
	static WebDriver driver;
	@Test

	public void nicelabel() throws InterruptedException, ScriptException, FileNotFoundException {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.edgedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		System.setProperty("webdriver.http.factory",  "jdk-http-client");
		//driver = new ChromeDriver();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);	
		genericMethods z = new genericMethods(driver);
		z.getURL("https://zsbportal-stage.zebra.com/");
		//google
		z.Click("xpath", "//*[@class='ping-button social-media continuewithgoogle']");
		z.sendText("xpath", "//*[@autocomplete='username']", "zebra79dvt");
		z.Click("xpath", "//*[text()='Next']");
		//z.socialAccAuthn("google");
		//z.Click("xpath", "//*[text()='Next']");
		z.sendText("xpath", "//*[@type='password']", "Zebra1281");
		z.delay(2000);
		z.Click("id", "passwordNext");
		z.delay(2000);
		z.refresh();
		z.refresh();
	
		z.Click("xpath", "//div[@data-testid='user-settings']");
		z.Click("xpath", "//span[contains(text(), 'Log Out')]");
		z.delay(2000);
		driver.close();

	}
}
