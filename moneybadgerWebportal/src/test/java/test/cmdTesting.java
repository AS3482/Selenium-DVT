package test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.genericMethods;

public class cmdTesting {

	static WebDriver driver;

	@Test
	public void test() throws InterruptedException, IOException, URISyntaxException{

		genericMethods z = new genericMethods(driver);
		z.setBrowser("chrome");
		z.getURL("https://zsbportal-stage.zebra.com/");
		z.Click("xpath", "//button[contains(text(),'Sign In with your email')]");
		z.sendText("id", "username", "zebraswtest2@hotmail.com");
		z.sendText("id", "password", "Zebratest123?");
		z.Click("id", "submit_id");
		Thread.sleep(4000);
		z.refresh();
		z.refresh();
		z.closeBrowser();

	}
}
