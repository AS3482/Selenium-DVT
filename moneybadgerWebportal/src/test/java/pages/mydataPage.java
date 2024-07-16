package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class mydataPage {
	
	// My Data page locators
	WebDriver driver = null;
	By button_gotIt = By.xpath("/html/body/div[2]/div[2]/div[3]/div/button/span");
	By button_mydata = By.linkText("My Data");
	By button_printersettings = By.linkText("Printer Settings");
	By button_printer = By.xpath("//*[text()=\"ZSB-DP12-alex2\"]");
	By textbox_search = By.xpath("//*[@placeholder='Search files']");	
	By button_x = By.xpath("//*[@data-testid='input-close-button']");
	By button_slider = By.xpath("//*[@data-testid='range-input']");
	By hidden_slider = By.xpath("//*[@value='7']");
	By highlighted_string = By.xpath("//*[@data-testid='highlighted-string']");
	By no_results = By.xpath("//*[@data-testid='no-results']");
	
	
	
	//constructor
	public mydataPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//click the Got It button on the alert message 
		public void clickGotit() {
			driver.findElement(button_gotIt).click();
		}
		//click the My Data button
		public void clickMydata() {
			driver.findElement(button_mydata).click();
		}
		
		// click the printer settings button
		public void clickPrinterSettings() {
			driver.findElement(button_printersettings).click();
		}
		
		//click on selected printer
		public void clickPrinter() {
			driver.findElement(button_printer).click();
		}
		
		//click slider
		public void slider() throws InterruptedException {
		WebElement element = driver.findElement(button_slider);		
		element.clear();		
		Actions actions=new Actions(driver);	 
	    actions.click(element).build().perform();
	    Thread.sleep(1000);
	    for (int i = 0; i < 5; i++){
	        actions.sendKeys(Keys.ARROW_LEFT).build().perform();
	        Thread.sleep(200);
	    }
	    Thread.sleep(2000);
	    String text= driver.findElement(By.xpath("//*[text()='Printer settings have been updated']")).getText();
	    System.out.println("This is the alert 1 " +text);
	    assertEquals("Printer settings have been updated", text, "printer settings are not matched");
	    element.clear();		
		Actions action=new Actions(driver);	 
	    actions.click(element).build().perform();
	    Thread.sleep(1000);
	    for (int i = 0; i < 6; i++){
	        action.sendKeys(Keys.ARROW_RIGHT).build().perform();
	        Thread.sleep(200);
	    }	   
		
		}
		
	//enter the file name
	public void enterFileName(String text) {	
		driver.findElement(textbox_search).sendKeys(text);
	}	
	
	
	//click the login button
	public void clickX() {
		driver.findElement(button_x).click();
	}
	//enter the file name
	public String getText() {	
		String ele = driver.findElement(highlighted_string).getText();
		return ele;
	}
	
	public String getAttribute() {
		String ele = driver.findElement(textbox_search).getAttribute("value");
		return ele;
	}
	
	//enter the file name
		public String getnoResultText() {	
			String ele = driver.findElement(no_results).getText();
			return ele;
		}

}
