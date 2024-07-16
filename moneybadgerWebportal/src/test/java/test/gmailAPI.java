package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class gmailAPI {
	static WebDriver driver;

		@Test
		public void gmailtesting() throws InterruptedException {			
			 

			WebDriverManager.chromedriver().setup();
			WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.http.factory",  "jdk-http-client");
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			//driver.get("https://accounts.google.com/ServiceLogin?");
			
			driver.get("https://mail.google.com/mail/u/0/#inbox");

			// gmail login
			driver.findElement(By.xpath("//*[@type='email']")).sendKeys("zsbuitest");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"identifierNext\"]")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@type='password']")).sendKeys("Zebra1281");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[text()='Next']")).click();
			Thread.sleep(10000);

			// some optional actions for reaching gmail inbox
//			driver.findElement(By.xpath("//*[@id='gbwa']")).click();
//			Thread.sleep(2000);
			//driver.findElement(By.xpath("//*[text()='Gmail']")).click();

			// now taking un-read email form inbox into a list
			//List<WebElement> unreademeil = driver.findElements(By.xpath("//*[@class='zF']"));

			// Mailer name for which i want to check do i have an email in my inbox 
			String MyMailer = "Zebra Technologies";
			//System.out.println("This is the email count "+unreademeil.size());
			
			driver.findElement(By.xpath("(//*[@name='Zebra Technologies'])[2]")).click();
			Thread.sleep(2000);
//			// real logic starts here
//			for(int i=0;i<unreademeil.size();i++){
//			    if(unreademeil.get(i).isDisplayed()==true){
//			        // now verify if you have got mail form a specific mailer (Note Un-read mails)
//			        // for read mails xpath loactor will change but logic will remain same
//			        if(unreademeil.get(i).getText().equals(MyMailer)){
//			            System.out.println("Yes we have got mail form " + MyMailer);
//			            // also you can perform more actions here 
//			            // like if you want to open email form the mailer
//			            break;
//			        }else{
//			            System.out.println("No mail form " + MyMailer);
//			        }
//			    }
			//}
			
	 
	 
	}

}
