package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {
	// login page locators
	WebDriver driver = null;
	By textbox_username = By.id("username");
	By textbox_password = By.id("password");
	By button_login = By.id("submit_id");
	By error_message = By.xpath("//div[@class='ping-error']");
	By button_google = By.linkText("Sign in with Google");
	By button_apple = By.linkText("Sign in with Apple");
	
	By textbox_gmail = By.id("identifierId");
	By textbox_gpassword = By.name("password");
	By button_gnext = By.xpath("//span[normalize-space()='Next']");
	
	By textbox_ausername = By.xpath("//input[@id='account_name_text_field']");
	By textbox_apassword = By.xpath("//input[@id='password_text_field']");
	By button_aenter = By.xpath("//button[@id='sign-in']");
	
	
	
	
	//constructor
	public loginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//enter the user name
	public void enterUsername(String text) {
		driver.findElement(textbox_username).clear();
		driver.findElement(textbox_username).sendKeys(text);
	}
	
	//enter the password	
	public void enterPassword(String text) {
		driver.findElement(textbox_password).clear();
		driver.findElement(textbox_password).sendKeys(text);
	}
	
	//click the login button
	public void clickLogin() {
		driver.findElement(button_login).click();
	}
	
	
	//click the gmail login button
	public void clickGoogle() {
		driver.findElement(button_google).click();
	}
	
	//click apple login button
	//click the gmail login button
	public void clickApple() {
		driver.findElement(button_apple).click();
	}
	
	//enter gmail address	
	public void enterGmail(String text) {
		driver.findElement(textbox_gmail).sendKeys(text);
	}
	
	//enter gmail password
	public void enterGpassword(String text) {
		driver.findElement(textbox_gpassword).sendKeys(text);
	}
	
	//click the next button on gmail login
	public void clickGnext() {
		driver.findElement(button_gnext).click();
	}
	
	//enter apple password
	public void enterAusername(String text) {
			driver.findElement(textbox_ausername).sendKeys(text);
		}
	
	//enter apple password
	public void enterApassword(String text) {
		driver.findElement(textbox_apassword).sendKeys(text);
	}
	
	//click the apple enter button
	public void clickAenter() {
		driver.findElement(button_aenter).click();
	}
	
	
//	//invalid user get message
//	public String errorMessage(String text) {
//		text = driver.findElement(error_message).getText();
//		return text;
//	}

}
