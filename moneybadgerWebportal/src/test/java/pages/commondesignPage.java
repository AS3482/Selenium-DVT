package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class commondesignPage {
	WebDriver driver = null;
	
	
	By button_commondesign = By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[1]/a[2]/div[2]");
	By button_printersettings = By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[1]/a[6]/div[2]");
	
	
	
	By button_printerdriver = By.xpath("/html/body/div[2]/div[2]/div[3]/div/button/span");
	By button_barcodes = By.xpath("//*[text()='Barcodes']");
	By button_basic = By.xpath("//*[text()='Basic']");
	By button_edit = By.xpath("//*[text()='Edit']");
	//By button_rename = By.xpath("//*[name()='svg' and @class='icon icon-edit']");
	//By button_rename = By.xpath("/html/body/app-root/designer-home/div/designer-label-info/div/div/div[1]/designer-icon");
	By button_rename = By.xpath("//designer-icon[@data-id='editIcon']");
	
	By button_printericon = By.xpath("(//*[name()='svg' and @width=25])[2]");
	
	
	//constructor
		public commondesignPage(WebDriver driver) {
			this.driver = driver;
		}
		
		//click alert message to download the printer drivers
		
		public void clickdriverMessage() {
			driver.findElement(button_printerdriver).click();
		}
		
		//click common design 
		public void clickCommonDesign() {
			driver.findElement(button_commondesign).click();
		}
		
		//click barcodes
		public void clickBarcodes() {
			driver.findElement(button_barcodes).click();
		}
		
		//click basic under barcodes
		public void clickBasic() {
			driver.findElement(button_basic).click();
		}
		
		//click edit 
		public void clickEdit() {
			driver.findElement(button_edit).click();
		}
		
		//click rename
		public void clickRename() {
			driver.findElement(button_rename).sendKeys(Keys.ENTER);;
		}
	

}
