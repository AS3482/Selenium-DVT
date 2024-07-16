package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.v100.dom.model.NodeId;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import config.config;
import io.github.bonigarcia.wdm.WebDriverManager;
import test.baseTest;

public class genericMethods extends baseTest{
	WebDriver driver=null;	
	public static String name = "DVT";
	String projectPath;
	String projectPathFile;
	String projectPathMain;


	//constructor
	public  genericMethods(WebDriver driver) {
		this.driver = driver;		
	}

	//setup browser
	public void setBrowser(String browser) throws InterruptedException, MalformedURLException {
		URL url = null;
		url = new URL("http://10.80.4.101:4444");
		//creating a directory
		projectPathMain = System.getProperty("user.dir");		 

		// check if the directory can be created 
		File f = new File(projectPathMain+"\\DownloadFiles\\"+execID);
		if (f.mkdir() == true) { 
			System.out.println("Directory has been created successfully"); 
			projectPath=projectPathMain+"\\DownloadFiles\\"+execID+"\\";
		}else { 
			System.out.println("Directory cannot be created"); 
		}

		// files directory can be created for uploading file
		File files = new File(projectPathMain+"\\files\\"+execID);
		if (files.mkdir() == true) {			
			projectPathFile=projectPathMain+"\\files\\"+execID+"\\";
			System.out.println("files Directory has been created successfully"); 
		} 
		else { 
			System.out.println("files Directory cannot be created"); 
		}

		//variable directory creation with execution id
		File variable = new File(projectPathMain+"\\variables\\"+execID);
		// check if the directory can be created 
		if (variable.mkdir() == true) { 
			System.out.println("Directory has been created successfully"); 			
		} 
		else { 
			System.out.println("Directory cannot be created"); 
		}

		System.out.println("this is the download path "+projectPath);
		System.out.println("this is the upload path "+projectPathFile);
		if(browser == "chrome") {		

			ChromeOptions options = new ChromeOptions();
			//			options.setCapability("networkname:applicationName","nodeTesting");
			//			options.setCapability("nodename:applicationName",nodeName);
			Map<String,Object>prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", projectPath);		
			options.setExperimentalOption("prefs", prefs);
			//options.addArguments("--disable-notifications");
			

			//desired capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			System.setProperty("webdriver.http.factory",  "jdk-http-client");
			//options.setCapability(EdgeOptions.CAPABILITY,options);
			caps.setCapability(ChromeOptions.CAPABILITY,options);			
			caps.setBrowserName(browser);	
			
			caps.setPlatform(Platform.WINDOWS);

			//URL url = new URL("http://10.80.4.108:5555");
			driver =  new RemoteWebDriver(url, caps);
			//driver.manage().deleteAllCookies();
			//Thread.sleep(7000);
			driver.manage().window().maximize();
			Thread.sleep(2000);
			Reporter.log("Browser is set "+browser);
			driver_chrome=driver;

		}else if(browser == "Edge"){						
			//initialize edge options
			EdgeOptions edgeoptions = new EdgeOptions();	
			//			edgeoptions.setCapability("networkname:applicationName","nodeTesting");
			//			edgeoptions.setCapability("nodename:applicationName",nodeName);
			Map<String,Object>prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", projectPath);
			edgeoptions.setExperimentalOption("prefs", prefs);			

			//desired capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			System.setProperty("webdriver.http.factory",  "jdk-http-client");

			edgeoptions.setCapability(EdgeOptions.CAPABILITY,edgeoptions);
			//caps.setCapability(EdgeOptions.CAPABILITY,edgeoptions);
			caps.setBrowserName("MicrosoftEdge");
			caps.setPlatform(Platform.WINDOWS);

			driver =  new RemoteWebDriver(url, caps);			
			driver.manage().window().maximize();
			Thread.sleep(2000);
			Reporter.log("Browser is set "+browser);
			driver_edge=driver;

		}else if(browser == "Firefox"){			
			//initiage firefox options
			FirefoxOptions options = new FirefoxOptions();
			//			options.setCapability("networkname:applicationName","nodeTesting");
			//			options.setCapability("nodename:applicationName",nodeName);
			Map<String,Object>prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", projectPath);
			options.addPreference("download.default_directory", projectPath);
			//desiredCapabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			System.setProperty("webdriver.http.factory",  "jdk-http-client");
			caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS,options);
			caps.setBrowserName("firefox");
			caps.setPlatform(Platform.WINDOWS);		

			driver =  new RemoteWebDriver(url, caps);
			driver.manage().window().maximize();
			Thread.sleep(2000);
			Reporter.log("Browser is set "+browser);
			driver_firefox=driver;
		}else if(browser == "Safari"){

			//initialize edge options
			//			SafariOptions options = new SafariOptions();	
			//			Map<String,Object>prefs = new HashMap<String, Object>();
			//			prefs.put("download.default_directory", projectPath);
			//			options.set
			//			options.setExperimentalOption("prefs", prefs);	
			//SafariDriver driver = new SafariDriver(options);

			//desired capabilities

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setBrowserName("safari");
			caps.setPlatform(Platform.MAC);
			SafariOptions.fromCapabilities(caps);
			//Capabilities capability=Capabilities().safari();
			System.setProperty("webdriver.http.factory",  "jdk-http-client");

			//caps.setCapability(SafariOptions.CAPABILITY,options);


			driver = new RemoteWebDriver(url, caps);
			driver.manage().window().maximize();
			Thread.sleep(2000);
			Reporter.log("Browser is set "+browser);
			driver_safari=driver;
		}
	}
	// get URL	
	public void getURL(String url) throws InterruptedException {
		driver.get(url);
		Thread.sleep(2000);
	}
	public void closeBrowser() {
		//driver.close();
		driver.quit();
		Reporter.log("Browser is closed");
	}
	public void quitBrowser() {
		driver.quit();
	}

	//click method needs 2 parameters 
	public void Click(String locator, String element) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement ele = null;
		if(locator=="xpath") {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).click();
			ele =	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		}else if (locator =="id") {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).click();
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
		}else 	if(locator =="name") {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).click();
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
		}else 	if(locator =="cssSelector") {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).click();
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
		}else 	if(locator =="linkText") {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).click();
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element)));
		} else 	if(locator =="partialLinkText") {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).click();
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element)));
		} else 	if(locator =="className") {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).click();
			ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element)));
		}	
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);

		//Thread.sleep(2000);
		Reporter.log("Clicked on the Element ");
		if(element.contains("loginbutton")||element.contains("submit_id")||element.contains("last nav-action")) {
			//Reporter.log("this is inside the if statement for ui "+element);			
			JavascriptExecutor js = (JavascriptExecutor) driver;		
			js.executeScript("localStorage.setItem('SMB_FEATURES', '[\"newHomeUI\"]');");
			Thread.sleep(2000);	
			Reporter.log("It is in new Home page ");
		}else if(element.contains("Next")||element.contains("Continue")) {
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;		
			js.executeScript("localStorage.setItem('SMB_FEATURES', '[\"newHomeUI\"]');");
			//driver.navigate().refresh();
			Thread.sleep(2000);	
			Reporter.log("It is in new Home page ");
			
		}
	}
	//click method needs 2 parameters 
		public void objectClick(String locator, String element) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));			
			if(locator=="xpath") {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).click();		
			}else if (locator =="id") {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).click();		
			}else 	if(locator =="name") {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).click();			
			}else 	if(locator =="cssSelector") {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).click();		
			}else 	if(locator =="linkText") {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).click();		
			} else 	if(locator =="partialLinkText") {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).click();			
			} else 	if(locator =="className") {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).click();			
			}		
			Reporter.log("Clicked on the Element.");
			Thread.sleep(2000);
		}





	//send text to an element needs 3 parameters
	public void sendText(String locator, String element, String text) throws FileNotFoundException {
		WebElement ele = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		if(locator=="xpath") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
			ele.clear();			
		}else if (locator=="id") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
			ele.clear();			
		}else if(locator=="name") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
			ele.clear();			
		}else if(locator=="cssSelector") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
			ele.clear();			
		}else if(locator=="linkText") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element)));
			ele.clear();			
		} else if(locator=="partialLinkText") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element)));
			ele.clear();			
		}else if(locator=="className") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element)));
			ele.clear();		
		}
		if (text.startsWith("@")) {			
			String filePath = System.getProperty("user.dir");			
			filePath = filePath+"\\variables\\"+execID+"\\"+text+".txt";				
			File file = new File(filePath);		            
			Scanner sc = new Scanner(file);
			sc.useDelimiter("\\Z");
			String value= sc.next();		
			ele.sendKeys(value);
			sc.close();
		}else{
			ele.sendKeys(text);
		}
		Reporter.log("Send Text to an element");

	}
	//keyboard actions methods
	public void keyboardActions(String locator, String element, String action, String text,int i) throws InterruptedException, FileNotFoundException {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement ele = null;
		String value =null;
		Actions ac=new Actions(driver);
		//getting text from variable
		if(text.startsWith("@")){			
			String filePath = System.getProperty("user.dir");			
			filePath = filePath+"\\variables\\"+execID+"\\"+text+".txt";			
			File file = new File(filePath);		            
			Scanner sc = new Scanner(file);
			sc.useDelimiter("\\Z");
			value= sc.next();
			System.out.println("this is the variable value "+value);
		}
		if(locator=="xpath") {	
			ele=driver.findElement(By.xpath(element));
			if(action.contains("enter")) {
				ele.clear();				
				if(text.startsWith("@")) {					
					ele.sendKeys(value);						
				}else {				
					ele.sendKeys(text);				}
				ele.sendKeys(Keys.ENTER);
				
			}else if (action.contains("up")) {
				for(int j=0; j<i;j++) {
					ele.sendKeys(Keys.ARROW_UP);		
				}}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						ele.sendKeys(Keys.ARROW_DOWN);
					}}else if (action.contains("rightClick")) {				
						ac.contextClick(ele).perform();
					}else if (action.contains("copy")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "v");
					}else if (action.contains("paste")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "c");
					}else if (action.contains("backspace")) {
						ele.sendKeys(Keys.BACK_SPACE);
					}else if (action.contains("delete")) {
						ele.sendKeys(Keys.CONTROL+ "A");
						ele.sendKeys(Keys.DELETE);
					}
		}else if (locator=="id") {
			ele=driver.findElement(By.id(element));
			if(action.contains("enter")) {				
				ele.clear();
				if (text.startsWith("@")) {
					ele.sendKeys(value);										
				}else {
					ele.sendKeys(text);
				}
				ele.sendKeys(Keys.ENTER);
			}else if (action.contains("up")) {
				for(int j=0; j<i;j++) {
					ele.sendKeys(Keys.ARROW_UP);				
				}}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						ele.sendKeys(Keys.ARROW_DOWN);
					}}else if (action.contains("rightClick")) {
						ac.contextClick(ele).perform();
					}else if (action.contains("copy")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "v");
					}else if (action.contains("paste")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "c");
					}else if (action.contains("backspace")) {
						ele.sendKeys(Keys.BACK_SPACE);
					}else if (action.contains("delete")) {
						ele.sendKeys(Keys.CONTROL+ "A");
						ele.sendKeys(Keys.DELETE);
					}
		}else if (locator=="name") {
			ele=driver.findElement(By.name(element));
			if(action.contains("enter")) {				
				ele.clear();
				if (text.startsWith("@")) {
					ele.sendKeys(value);										
				}else {
					ele.sendKeys(text);
				}
				ele.sendKeys(Keys.ENTER);
			}else if (action.contains("up")) {
				for(int j=0; j<i;j++) {
					ele.sendKeys(Keys.ARROW_UP);				
				}}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						ele.sendKeys(Keys.ARROW_DOWN);
					}}else if (action.contains("rightClick")) {				
						ac.contextClick(ele).perform();
					}else if (action.contains("copy")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "v");
					}else if (action.contains("paste")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "c");
					}else if (action.contains("backspace")) {
						ele.sendKeys(Keys.BACK_SPACE);
					}else if (action.contains("delete")) {
						ele.sendKeys(Keys.CONTROL+ "A");
						ele.sendKeys(Keys.DELETE);
					}
		}else if (locator=="cssSelector") {
			ele=driver.findElement(By.cssSelector(element));
			if(action.contains("enter")) {					
				ele.clear();
				if (text.startsWith("@")) {
					ele.sendKeys(value);										
				}else {
					ele.sendKeys(text);
				}
				ele.sendKeys(Keys.ENTER);
			}else if (action.contains("up")) {
				for(int j=0; j<i;j++) {
					ele.sendKeys(Keys.ARROW_UP);				
				}}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						ele.sendKeys(Keys.ARROW_DOWN);
					}}else if (action.contains("rightClick")) {				
						ac.contextClick(ele).perform();
					}else if (action.contains("copy")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "v");
					}else if (action.contains("paste")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "c");
					}else if (action.contains("backspace")) {
						ele.sendKeys(Keys.BACK_SPACE);
					}else if (action.contains("delete")) {
						ele.sendKeys(Keys.CONTROL+ "A");
						ele.sendKeys(Keys.DELETE);
					}
		}else if (locator=="linkText") {
			ele=driver.findElement(By.linkText(element));
			if(action.contains("enter")) {				
				ele.clear();
				if (text.startsWith("@")) {
					ele.sendKeys(value);										
				}else {
					ele.sendKeys(text);
				}
				ele.sendKeys(Keys.ENTER);
			}else if (action.contains("up")) {
				for(int j=0; j<i;j++) {
					ele.sendKeys(Keys.ARROW_UP);				
				}}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						ele.sendKeys(Keys.ARROW_DOWN);
					}}else if (action.contains("rightClick")) {				
						ac.contextClick(ele).perform();
					}else if (action.contains("copy")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "v");
					}else if (action.contains("paste")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "c");
					}else if (action.contains("backspace")) {
						ele.sendKeys(Keys.BACK_SPACE);
					}else if (action.contains("delete")) {
						ele.sendKeys(Keys.CONTROL+ "A");
						ele.sendKeys(Keys.DELETE);
					}
		}else if (locator=="partialLinkText") {
			ele=driver.findElement(By.partialLinkText(element));
			if(action.contains("enter")) {				
				ele.clear();
				if (text.startsWith("@")) {
					ele.sendKeys(value);										
				}else {
					ele.sendKeys(text);
				}
				ele.sendKeys(Keys.ENTER);
			}else if (action.contains("up")) {
				for(int j=0; j<i;j++) {
					ele.sendKeys(Keys.ARROW_UP);				
				}}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						ele.sendKeys(Keys.ARROW_DOWN);
					}}else if (action.contains("rightClick")) {				
						ac.contextClick(ele).perform();
					}else if (action.contains("copy")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "v");
					}else if (action.contains("paste")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "c");
					}else if (action.contains("backspace")) {
						ele.sendKeys(Keys.BACK_SPACE);
					}else if (action.contains("delete")) {
						ele.sendKeys(Keys.CONTROL+ "A");
						ele.sendKeys(Keys.DELETE);
					}
		}else if (locator=="className") {
			ele=driver.findElement(By.className(element));
			if(action.contains("enter")) {				
				ele.clear();
				if (text.startsWith("@")) {
					ele.sendKeys(value);										
				}else {
					ele.sendKeys(text);
				}
				ele.sendKeys(Keys.ENTER);
			}else if (action.contains("up")) {
				for(int j=0; j<i;j++) {
					ele.sendKeys(Keys.ARROW_UP);				
				}}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						ele.sendKeys(Keys.ARROW_DOWN);
					}}else if (action.contains("rightClick")) {				
						ac.contextClick(ele).perform();
					}else if (action.contains("copy")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "v");
					}else if (action.contains("paste")) {
						ele.click();
						ele.sendKeys(Keys.CONTROL+ "c");
					}else if (action.contains("backspace")) {
						ele.sendKeys(Keys.BACK_SPACE);
					}else if (action.contains("delete")) {
						ele.sendKeys(Keys.CONTROL+ "A");
						ele.sendKeys(Keys.DELETE);
					}
		}
		Thread.sleep(2000);
	}
	//get attribute and compare
	public void getAttributeAndCompare(String locator, String element, String attrName,String expected) throws FileNotFoundException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		String txt = null;
		if(locator=="xpath") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).getAttribute(attrName);
		}else if (locator=="id") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).getAttribute(attrName);
		}else 	if(locator=="name") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).getAttribute(attrName);
		}else 	if(locator=="cssSelector") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).getAttribute(attrName);
		}else 	if(locator=="linkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).getAttribute(attrName);
		} else 	if(locator=="partialLinkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).getAttribute(attrName);
		}else 	if(locator=="className") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).getAttribute(attrName);
		}
		if(expected.startsWith("@")){			
			System.out.println("this is the path of the file "+System.getProperty("user.dir"));
			String filePath = System.getProperty("user.dir");			
			filePath = filePath+"\\variables\\"+execID+"\\"+expected+".txt";
			System.out.println("this is the path of file aftter variable "+filePath);
			File file = new File(filePath);		            
			Scanner sc = new Scanner(file);
			sc.useDelimiter("\\Z");
			String value= sc.next();
			System.out.println("this is the variable value "+value);
			System.out.println("this is the text value "+txt);
			//config.softAssert.assertEquals(txt,value,"Actual  is "+txt+" is not same as expected  "+sc.next());
			assertEquals(value, txt);
			Reporter.log("Actual Text is "+txt+" compared with expected Text "+value);
			sc.close();
		}else {
			//config.softAssert.assertEquals(txt,expected,"Actual "+txt+"is not same as expected "+expected);
			assertEquals(expected, txt);
			Reporter.log("Actual Text is "+expected+" compared with expected Text "+txt);
		}
	}
	
	//get Css and compare
		public void getCSSAndCompare(String locator, String element, String attrName,String expected) throws FileNotFoundException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			String txt = null;
			if(locator=="xpath") {
				txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).getCssValue(attrName);
			}else if (locator=="id") {
				txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).getCssValue(attrName);
			}else 	if(locator=="name") {
				txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).getCssValue(attrName);
			}else 	if(locator=="cssSelector") {
				txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).getCssValue(attrName);
			}else 	if(locator=="linkText") {
				txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).getCssValue(attrName);
			} else 	if(locator=="partialLinkText") {
				txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).getCssValue(attrName);
			}else 	if(locator=="className") {
				txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).getCssValue(attrName);
			}
			txt = txt.substring(0,txt.indexOf(")")+1);
			if(expected.startsWith("@")){			
				System.out.println("this is the path of the file "+System.getProperty("user.dir"));
				String filePath = System.getProperty("user.dir");			
				filePath = filePath+"\\variables\\"+execID+"\\"+expected+".txt";
				System.out.println("this is the path of file aftter variable "+filePath);
				File file = new File(filePath);		            
				Scanner sc = new Scanner(file);
				sc.useDelimiter("\\Z");
				String value= sc.next();
				System.out.println("this is the variable value "+value);
				System.out.println("this is the text value "+txt);
				//config.softAssert.assertEquals(txt,value,"Actual  is "+txt+" is not same as expected  "+sc.next());
				assertEquals(value, txt);
				Reporter.log("Actual Text is "+txt+" compared with expected Text "+value);
				sc.close();
			}else {
				//config.softAssert.assertEquals(txt,expected,"Actual "+txt+"is not same as expected "+expected);
				assertEquals(expected, txt);
				Reporter.log("Actual Text is "+expected+" compared with expected Text "+txt);
			}
		}



	//set attribute
	public void setAttribute(String locator, String element, String attr, String value,String option) throws FileNotFoundException {		
		WebElement ele = null;		
		if(locator=="xpath") {
			ele = driver.findElement(By.xpath(element));
		}else if (locator=="id") {
			ele=driver.findElement(By.id(element));
		}else 	if(locator=="name") {
			ele=driver.findElement(By.name(element));
		}else 	if(locator=="cssSelector") {
			ele=driver.findElement(By.cssSelector(element));
		}else 	if(locator=="linkText") {
			ele=driver.findElement(By.linkText(element));
		} else 	if(locator=="partialLinkText") {
			ele=driver.findElement(By.partialLinkText(element));
		}else 	if(locator=="className") {
			ele=driver.findElement(By.className(element));
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0]."+attr+"='"+value+"';", ele);
		if (option.equals("Yes")) {
			ele.sendKeys(Keys.ARROW_RIGHT);
			Reporter.log("Clicked the element for set attribute");
		}


	}

	//clear text 
	public void clearText(String locator, String element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		if(locator=="xpath") {				
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).clear();				
		}else if (locator=="id") {				
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).clear();
		}else if(locator=="name") {				
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).clear();
		}else if(locator=="cssSelector") {			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).clear();				
		}else if(locator=="linkText") {			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).clear();
		} else 	if(locator=="partialLinkText") {		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).clear();
		}else 	if(locator=="className") {			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).clear();				
		}
		Reporter.log("Cleared the Text of an element");
	}


	/*	//keyboard actions methods
		public void keyboardActions(String locator, String element, String action, String text,int i) {		
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement ele = null;
			if(locator=="xpath") {		
				if(action.contains("enter")) {
					ele=driver.findElement(By.xpath(element));
					ele.clear();
					ele.sendKeys(text);
					ele.sendKeys(Keys.ENTER);
				}else if (action.contains("up")) {
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).sendKeys(Keys.ARROW_UP);				
					}
				}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).sendKeys(Keys.ARROW_DOWN);
					}
				}
			}else if (locator=="id") {
				if(action.contains("enter")) {
					ele=driver.findElement(By.id(element));
					ele.clear();
					ele.sendKeys(text);
					ele.sendKeys(Keys.ENTER);
				}else if (action.contains("up")) {
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).sendKeys(Keys.ARROW_UP);				
					}
				}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).sendKeys(Keys.ARROW_DOWN);
					}
				}

			}else if (locator=="name") {
				if(action.contains("enter")) {
					ele=driver.findElement(By.name(element));
					ele.clear();
					ele.sendKeys(text);
					ele.sendKeys(Keys.ENTER);
				}else if (action.contains("up")) {
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).sendKeys(Keys.ARROW_UP);				
					}
				}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).sendKeys(Keys.ARROW_DOWN);
					}
				}
			}else if (locator=="cssSelector") {
					if(action.contains("enter")) {
						ele=driver.findElement(By.cssSelector(element));
						ele.clear();
						ele.sendKeys(text);
						ele.sendKeys(Keys.ENTER);
					}else if (action.contains("up")) {
						for(int j=0; j<i;j++) {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).sendKeys(Keys.ARROW_UP);				
						}
					}else if(action.contains("down")){
						for(int j=0; j<i;j++) {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).sendKeys(Keys.ARROW_DOWN);
						}
					}
			}else if (locator=="linkText") {
				if(action.contains("enter")) {
					ele=driver.findElement(By.linkText(element));
					ele.clear();
					ele.sendKeys(text);
					ele.sendKeys(Keys.ENTER);
				}else if (action.contains("up")) {
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).sendKeys(Keys.ARROW_UP);				
					}
				}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).sendKeys(Keys.ARROW_DOWN);
					}
				}
			}else if (locator=="partialLinkText") {
				if(action.contains("enter")) {
					ele=driver.findElement(By.partialLinkText(element));
					ele.clear();
					ele.sendKeys(text);
					ele.sendKeys(Keys.ENTER);
				}else if (action.contains("up")) {
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).sendKeys(Keys.ARROW_UP);				
					}
				}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).sendKeys(Keys.ARROW_DOWN);
					}
				}
			}else if (locator=="className") {
				if(action.contains("enter")) {
					ele=driver.findElement(By.className(element));
					ele.clear();
					ele.sendKeys(text);
					ele.sendKeys(Keys.ENTER);
				}else if (action.contains("up")) {
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).sendKeys(Keys.ARROW_UP);				
					}
				}else if(action.contains("down")){
					for(int j=0; j<i;j++) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).sendKeys(Keys.ARROW_DOWN);
					}
				}		

			}
			}*/


	public String getTitle1() {		
		String title = driver.getTitle();
		return title;
	}

	public void getTitle(String expectedTitle) {
		String text = driver.getTitle();
		assertEquals(expectedTitle, text);
		Reporter.log("Actual Title is "+ expectedTitle+" compared with expected Title "+text);

	}


	public String getText(String locator, String element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		String txt = null;
		if(locator=="xpath") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).getText();
		}else if (locator=="id") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).getText();
		}else 	if(locator=="name") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).getText();
		}else 	if(locator=="cssSelector") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).getText();
		}else 	if(locator=="linkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).getText();
		} else 	if(locator=="partialLinkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).getText();
		}else 	if(locator=="className") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).getText();
		}
		return txt;

	}

	public void getTextCompare(String locator, String element, String expected) throws FileNotFoundException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		String txt = null;
		if(locator=="xpath") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).getText();
		}else if (locator=="id") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).getText();
		}else 	if(locator=="name") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).getText();
		}else 	if(locator=="cssSelector") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).getText();
		}else 	if(locator=="linkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).getText();
		} else 	if(locator=="partialLinkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).getText();
		}else 	if(locator=="className") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).getText();
		}
		if(expected.startsWith("@")){				
			System.out.println("this is the path of the file "+System.getProperty("user.dir"));
			String filePath = System.getProperty("user.dir");			
			filePath = filePath+"\\variables\\"+execID+"\\"+expected+".txt";
			System.out.println("this is the path of file aftter variable "+filePath);
			File file = new File(filePath);		            
			Scanner sc = new Scanner(file);
			sc.useDelimiter("\\Z");
			String value= sc.next();			
			//config.softAssert.assertEquals(txt,value,"Actual  is "+txt+" is not same as expected  "+sc.next());
			assertEquals(value, txt);
			Reporter.log("Actual Text is "+txt+" compared with expected Text "+value);
			sc.close();
		}else if(expected.startsWith("(")) {	
			expected = expected.substring(1, expected.length() - 1);			
			Pattern p = Pattern.compile(expected);
			java.util.regex.Matcher m = p.matcher(txt);	 		
			assertEquals(true, m.matches());
			Reporter.log("Actual Text is "+expected+" compared with expected Text "+txt);			

		}else if(expected.contains("currentDate")) {
			SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");  
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			java.util.Date date1 = new  java.util.Date();			
			expected =expected.replace("currentDate", formatter.format(date1));
			assertEquals(expected, txt);
			Reporter.log("Actual Text is "+expected+" compared with expected Text "+txt);			

		}
		else {
			//config.softAssert.assertEquals(txt,expected,"Actual "+txt+"is not same as expected "+expected);		
			assertEquals(expected, txt);
			Reporter.log("Actual Text is "+expected+" compared with expected Text "+txt);
		}
		//		assertEquals(expected, txt);
		//		Reporter.log("Actual Text is "+expected+" compared with expected Text "+txt);
	}


	//switch to Frame
	public void switchToFrame(String locator, String element) {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement ele = null;
		if(locator=="xpath") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		}else if (locator=="id") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
		}else 	if(locator=="name") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
		}else 	if(locator=="cssSelector") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
		}else 	if(locator=="linkText") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element)));
		} else 	if(locator=="partialLinkText") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element)));
		}else 	if(locator=="className") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element)));
		}		
		driver.switchTo().frame(ele);		
		Reporter.log("The Webpage is switch to Frame");	
	}



	//file download
	public void downloadFile(String locator, String element, String fileName) throws InterruptedException {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement ele = null;
		if(locator=="xpath") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		}else if (locator=="id") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
		}else 	if(locator=="name") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
		}else 	if(locator=="cssSelector") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
		}else 	if(locator=="linkText") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element)));
		} else 	if(locator=="partialLinkText") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element)));
		}else 	if(locator=="className") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element)));
		}		
		ele.click();
		Thread.sleep(3000);
		Reporter.log("File is downloaded");
		if(fileName.isEmpty()==false) { 		
			projectPath = System.getProperty("user.dir");
			File f1=new File(projectPath+"\\DownloadFiles\\"+execID+"\\"+fileName); 
			//config.softAssert.assertEquals(true, f1.exists(),"file doesn't exist ");			 
			assertEquals(true, f1.exists(),"file doesn't exist ");
			Reporter.log(fileName + " File is downloaded successfully");
		}
	}

	//file upload
	public void uploadFile(String locator, String element, String fileName, String cid) {
		projectPath = System.getProperty("user.dir");
		File source = new File(projectPath+"\\uploadFile\\"+cid+"\\"+fileName);
		File dest = new File(projectPath+"\\files\\"+execID+"\\"+fileName);
		System.out.println("this is the source path "+source);
		System.out.println("this is the dest path "+dest);
		try {
			FileUtils.copyFile(source, dest);
			System.out.println("file created ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		WebElement ele = null;
		if(locator=="xpath") {
			ele = driver.findElement(By.xpath(element));
		}else if (locator=="id") {
			ele = driver.findElement(By.id(element));
		}else 	if(locator=="name") {
			ele =driver.findElement(By.name(element));
		}else 	if(locator=="cssSelector") {
			ele = driver.findElement(By.cssSelector(element));
		}else 	if(locator=="linkText") {
			ele = driver.findElement(By.linkText(element));
		} else 	if(locator=="partialLinkText") {
			ele = driver.findElement(By.partialLinkText(element));
		}else 	if(locator=="className") {
			ele = driver.findElement(By.className(element));
		}		

		System.out.println("this is the upload path "+projectPathFile);
		((RemoteWebElement)ele).setFileDetector(new LocalFileDetector());
		ele.sendKeys(projectPathFile+fileName);			
		Reporter.log(fileName+" File has been uploaded on webpage");	
	}



/*	//get substring and compare with variable or expected
	public void getSubstringAndCompare(String locator, String element, String expected, String i, String objectOption) throws FileNotFoundException, InterruptedException {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String txt = null;
		if(locator=="xpath") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).getText();
		}else if (locator=="id") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).getText();
		}else 	if(locator=="name") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).getText();
		}else 	if(locator=="cssSelector") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).getText();
		}else 	if(locator=="linkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).getText();
		} else 	if(locator=="partialLinkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).getText();
		}else 	if(locator=="className") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).getText();
		}
		if(objectOption.isEmpty()) {
			if(expected.startsWith("@")){				
				System.out.println("this is the path of the file "+System.getProperty("user.dir"));
				String filePath = System.getProperty("user.dir");			
				filePath = filePath+"\\variables\\"+execID+"\\"+expected+".txt";
				System.out.println("this is the path of file aftter variable "+filePath);
				File file = new File(filePath);		            
				Scanner sc = new Scanner(file);
				sc.useDelimiter("\\Z");
				String value= sc.next();		
				value = value.replaceAll("[^0-9]", "");			
				int jvalue = Integer.parseInt(value);			
				txt = txt.replaceAll("[^0-9]", "");			
				int jtxt=Integer.parseInt(txt);			
				int j=Integer.parseInt(i);			
				jvalue = jvalue+(j);			
				//config.softAssert.assertEquals(txt,value,"Actual  is "+txt+" is not same as expected  "+sc.next());
				assertEquals(jvalue, jtxt);
				Reporter.log("Actual substring Text is "+jtxt+" compared with expected substring Text "+jvalue);
				sc.close();
			}
		}else {
			if(expected.startsWith("@")){
				int obj=Integer.parseInt(objectOption);
				obj=obj-1;
				System.out.println("this is the objectoption");
				System.out.println("this is the path of the file "+System.getProperty("user.dir"));
				String filePath = System.getProperty("user.dir");			
				filePath = filePath+"\\variables\\"+execID+"\\"+expected+".txt";
				System.out.println("this is the path of file aftter variable "+filePath);
				File file = new File(filePath);		            
				Scanner sc = new Scanner(file);
				sc.useDelimiter("\\Z");
				String value= sc.next();

				Pattern p = Pattern.compile(" ([0-9]+) ");
				java.util.regex.Matcher m = p.matcher(value);	
				System.out.println("this is the matcher "+m);				
				String valuegrp0 = null;
				m.find();
				valuegrp0 = m.group(obj); 				
				int jvalue = Integer.parseInt(valuegrp0.trim());
				int j=Integer.parseInt(i);
				jvalue = jvalue+(j);	

				java.util.regex.Matcher m1 = p.matcher(txt);	 	
				String txtgrp0 = null;	
				m1.find();
				txtgrp0 = m1.group(0);			
				int jtxt = Integer.parseInt(txtgrp0.trim());

				//config.softAssert.assertEquals(txt,value,"Actual  is "+txt+" is not same as expected  "+sc.next());
				assertEquals(jvalue, jtxt);
				Reporter.log("Actual substring Text is "+jtxt+" compared with expected substring Text "+jvalue);
				sc.close();
			}			
		}		

	}*/
	
	
	//get substring and compare with variable or expected
	public void getSubstringAndCompare(String locator, String element, String expected, String i, String objectOption) throws FileNotFoundException, InterruptedException {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String txt = null;
		if(locator=="xpath") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).getText();
		}else if (locator=="id") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).getText();
		}else 	if(locator=="name") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).getText();
		}else 	if(locator=="cssSelector") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).getText();
		}else 	if(locator=="linkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).getText();
		} else 	if(locator=="partialLinkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).getText();
		}else 	if(locator=="className") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).getText();
		}
		if(objectOption.isEmpty()) {
			if(expected.startsWith("@")){					
			String filePath = System.getProperty("user.dir");			
			filePath = filePath+"\\variables\\"+execID+"\\"+expected+".txt";					
			File file = new File(filePath);		            
			Scanner sc = new Scanner(file);
			sc.useDelimiter("\\Z");
			String value= sc.next();
						
			//getting integers from a variable 
			value = value.replaceFirst("^[^\\-\\d]*", ""); //remove the leading non-digits if any
			int valueInt = Integer.parseInt(value);					

			// getting integers from elements text
			txt = txt.replaceFirst("^[^\\-\\d]*", ""); //remove the leading non-digits if any
			int txtInt = Integer.parseInt(txt);
						
			//getting value to increment or decrement
			int j=Integer.parseInt(i);					
			System.out.println("this is the i value "+j);
						
			//subtracting the no. of labels
			valueInt = valueInt+(j);					
						
			//config.softAssert.assertEquals(txt,value,"Actual  is "+txt+" is not same as expected  "+sc.next());
			assertEquals(valueInt, txtInt);
			Reporter.log("Actual substring Text is "+txtInt+" compared with expected substring Text "+valueInt);
			sc.close();
					}
		}else {
			if(expected.startsWith("@")){
				int obj=Integer.parseInt(objectOption);
				obj=obj-1;				
				String filePath = System.getProperty("user.dir");			
				filePath = filePath+"\\variables\\"+execID+"\\"+expected+".txt";										
				File file = new File(filePath);		            
				Scanner sc = new Scanner(file);
				sc.useDelimiter("\\Z");
				String value= sc.next();
						
				//getting integers from a variable 
				value = value.replaceFirst("^[^\\-\\d]*", ""); //remove the leading non-digits if any
				String[] valNum = value.split("[^\\-\\d]+"); //split
				int valueInt = Integer.parseInt(valNum[obj]);					
						
				// getting integers from elements text
				txt = txt.replaceFirst("^[^\\-\\d]*", ""); //remove the leading non-digits if any
				String[] txtNum = txt.split("[^\\-\\d]+"); //split
				int txtInt = Integer.parseInt(txtNum[obj]);						
				
				int j=Integer.parseInt(i);
				valueInt = valueInt+(j);		

				//config.softAssert.assertEquals(txt,value,"Actual  is "+txt+" is not same as expected  "+sc.next());
				assertEquals(valueInt, txtInt);
				Reporter.log("Actual substring Text is "+txtInt+" compared with expected substring Text "+valueInt);
				sc.close();
		}			
	}		

}
	//rename label on design page
	public void renameLabel(String labelName) throws InterruptedException {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Designer']")));
		driver.findElement(By.xpath("//*[@data-id='editIcon']")).click();
		Thread.sleep(2000);
		WebElement ele1=driver.findElement(By.xpath("//input[@data-id='label-name-input']"));
		ele1.click();
		ele1.sendKeys(labelName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='upper-canvas ']")).click();
	}
	//delete the label the condition it should be at mydesigns page
	public void deleteLabel(String labelName) throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='"+labelName+"']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Delete']")).click();
		driver.findElement(By.xpath("//*[@data-testid='template-delete-button']")).click();
	}


	//getting the object on upper canvas
	public void dropOnCanvas(String locator, String element, int x, int y, String varName) throws InterruptedException, IOException {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement ele = null;
		if(locator=="xpath") {			
			ele =	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		}else if (locator =="id") {			
			ele =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
		}else 	if(locator =="name") {			
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
		}else 	if(locator =="cssSelector") {		
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
		}else 	if(locator =="linkText") {			
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element)));
		} else 	if(locator =="partialLinkText") {		
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element)));
		} else 	if(locator =="className") {			
			ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element)));
		}	
		action.moveToElement(ele,x,y).click().moveByOffset(14, 0).build().perform();
		Thread.sleep(2000);
		Reporter.log("Dropped on Canvas");
		if (!varName.isEmpty()) {
			String filePath = System.getProperty("user.dir");
			File f = new File(filePath+"\\variables\\"+execID);
			filePath=filePath+"\\variables\\"+execID+"\\";		
			f = new File(filePath+varName+".txt");
			if(f.createNewFile()==true) {
				System.out.println("Created new file successfully");
				filePath=filePath+varName+".txt";
			}
			//WebElement titleEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='title'])[6]")));
			WebElement titleEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='header'] span[class='title']")));
			String txt = titleEle.getText();				
			FileWriter fw=new FileWriter(filePath); 
			fw.write(txt); 			
			fw.close();
			Reporter.log("The title name of the label is saved in  a variable " +varName);
			}
	}

	// zoom the label
	public void zoom(String zoomperctValue) {
		WebElement zoomLevel= driver.findElement(By.xpath("//*[@data-id='zoomLevel']"));
		WebElement zoomIn= driver.findElement(By.xpath("//*[@data-id='zoomIn']"));
		WebElement zoomOut= driver.findElement(By.xpath("//*[@data-id='zoomOut']"));
		String zoomLevel1 = zoomLevel.getText();
		zoomLevel1=zoomLevel1.replace("%", "");
		int level = Integer.parseInt(zoomLevel1);
		int zoomperct = Integer.parseInt(zoomperctValue);
		while(level<zoomperct) {
			zoomIn.click();			
			zoomLevel1 = zoomLevel.getText();
			zoomLevel1=zoomLevel1.replace("%", "");
			level = Integer.parseInt(zoomLevel1);
			System.out.println("this is the zoom level after opening the label "+zoomLevel1);
			if(level==zoomperct) {
				break;
			}
		}
		while(level>zoomperct) {			
			zoomOut.click();
			zoomLevel1 = zoomLevel.getText();
			zoomLevel1=zoomLevel1.replace("%", "");
			level = Integer.parseInt(zoomLevel1);
			System.out.println("this is the zoom level after opening the label "+zoomLevel1);		
			if(level==zoomperct) {
				break;
			}
		}
		Reporter.log("This is the zoom level "+zoomLevel1);
	}



	//image rotate
	public void rotate(String degree) {			
		driver.findElement(By.xpath("//*[@class='icon icon-rotate']")).click();
		driver.findElement(By.xpath("//*[@class='icon icon-rotate-"+degree+"']")).click();
		Reporter.log("Rotate Image by "+degree);
	}
	
	
	//edit the properties dialogue on canvas
	public void editCanvasElement(String exp) throws FileNotFoundException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int i = 1;
		String text=null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		if(exp.startsWith("@")){			
			String filePath = System.getProperty("user.dir");			
			filePath = filePath+"\\variables\\"+execID+"\\"+exp+".txt";				
			File file = new File(filePath);		            
			Scanner sc = new Scanner(file);
			sc.useDelimiter("\\Z");
			String value= sc.next();				
			sc.close();
			do{
				js.executeScript("var c = document.querySelector(\"canvas[data-id='main-canvas']\").NiceLabelFabricCanvas;"
					+ "var objects = c.getObjects();"				
					+ "c.setActiveObject(objects["+i+"]);"
					+"c.renderAll();");	
				//WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='title'])[6]")));
				WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='header'] span[class='title']")));
				text = ele.getText();					
				i++;
				}while(!text.equals(value));
						Reporter.log("Edited the property menu for selected element on canvas in if "+value);
						
		}else {
			do{
				js.executeScript("var c = document.querySelector(\"canvas[data-id='main-canvas']\").NiceLabelFabricCanvas;"
						+ "var objects = c.getObjects();"				
						+ "c.setActiveObject(objects["+i+"]);"
						+"c.renderAll();");	
				//WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='title'])[6]")));
				WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='header'] span[class='title']")));
				text = ele.getText();					
				i++;
				}while(!text.equals(exp));
					Reporter.log("Edited the property menu for selected element on canvas in else"+text);
		}
	}
	
	//get text and save it in a filerefresh
	public void getTextSaveInVaraible(String locator, String element, String varName) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		String txt = null;
		String var=null;
		if(locator=="windowHandle") {
			txt = driver.getWindowHandle();				
		}else if(locator=="xpath") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).getText();
		}else if (locator=="id") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).getText();
		}else 	if(locator=="name") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).getText();
		}else 	if(locator=="cssSelector") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).getText();
		}else 	if(locator=="linkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).getText();
		} else 	if(locator=="partialLinkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).getText();
		}else 	if(locator=="className") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).getText();
		}	
		String filePath = System.getProperty("user.dir");
		//		File f = new File(filePath+"\\variables\\"+execID);
		//		// check if the directory can be created 
		//		if (f.mkdir() == true) { 
		//			System.out.println("Directory has been created successfully"); 
		filePath=filePath+"\\variables\\"+execID+"\\";
		//		} 
		//		else { 
		//			System.out.println("Directory cannot be created"); 
		//		}
		File f = new File(filePath+varName+".txt");

		if(f.createNewFile()==true) {
			System.out.println("Created new file successfully");
			filePath=filePath+varName+".txt";
		}
		//filePath = filePath+"\\variables\\"+execID+"\\"+varName+".txt";	
		System.out.println("this is the file path "+filePath);			

		FileWriter fw=new FileWriter(filePath); 
		fw.write(txt); 			
		fw.close();
		Reporter.log("The variable value is " +txt);				
	}




	//get data from varaible or expected and compare
	public void getDataCompare(String locator, String element, String expected,String varType, String i) throws FileNotFoundException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		String txt = null;
		if(locator=="xpath") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))).getText();
		}else if (locator=="id") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).getText();
		}else 	if(locator=="name") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element))).getText();
		}else 	if(locator=="cssSelector") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))).getText();
		}else 	if(locator=="linkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))).getText();
		} else 	if(locator=="partialLinkText") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element))).getText();
		}else 	if(locator=="className") {
			txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element))).getText();
		}
		if(expected.startsWith("@")){
			if(varType=="string") {	
				System.out.println("this is the path of the file "+System.getProperty("user.dir"));
				String filePath = System.getProperty("user.dir");			
				filePath = filePath+"\\variables\\"+execID+"\\"+expected+".txt";
				System.out.println("this is the path of file aftter variable "+filePath);
				File file = new File(filePath);		            
				Scanner sc = new Scanner(file);
				sc.useDelimiter("\\Z");
				String value= sc.next();
				System.out.println("this is the variable value "+value);
				System.out.println("this is the text value "+txt);
				//config.softAssert.assertEquals(txt,value,"Actual  is "+txt+" is not same as expected  "+sc.next());
				assertEquals(value, txt);
				Reporter.log("Actual Text is "+txt+" compared with expected Text "+value);
				sc.close();
			}else if (varType=="int") {				
				String filePath = System.getProperty("user.dir");			
				filePath = filePath+"\\variables\\"+execID+"\\"+expected+".txt";
				System.out.println("this is the path of the file "+filePath);
				File file = new File(filePath);		            
				Scanner sc = new Scanner(file);
				sc.useDelimiter("\\Z");			
				String value= sc.next();
				int j = Integer.parseInt(i);
				System.out.println(value);
				int number = Integer.parseInt(value);
				number=number+(j);
				System.out.println(number);	
				//config.softAssert.assertEquals(Integer.parseInt(txt),number,"Actual  is "+txt+" is not same as expected  "+number);
				assertEquals(number, Integer.parseInt(txt));
				Reporter.log("Actual Text is "+txt+" compared with expected Text "+number);
				sc.close();

			}			
		}else{
			//config.softAssert.assertEquals(txt,expected,"Actual  is "+txt+" is not same as expected  "+expected);
			assertEquals(expected, txt);
			Reporter.log("Actual Text is "+expected+" compared with expected Text "+txt);
		}
	}

	//switch to main frame
	public void switchToMainFrame() {
		driver.switchTo().parentFrame();
	}

	//scroll to element
	public void scrollToElement(String locator, String element) throws InterruptedException {
		WebElement ele = null;
		if(locator=="xpath") {
			ele = driver.findElement(By.xpath(element));
		}else if (locator=="id") {
			ele = driver.findElement(By.id(element));
		}else 	if(locator=="name") {
			ele =driver.findElement(By.name(element));
		}else 	if(locator=="cssSelector") {
			ele = driver.findElement(By.cssSelector(element));
		}else 	if(locator=="linkText") {
			ele = driver.findElement(By.linkText(element));
		} else 	if(locator=="partialLinkText") {
			ele = driver.findElement(By.partialLinkText(element));
		}else 	if(locator=="className") {
			ele = driver.findElement(By.className(element));
		}		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(500);

	}

	//Dialogue pass or fail	
	public void dialogueWindow(String option,String message) {	
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		if(option == "Pass/Fail") {
			String[] options = {"Pass", "Fail"};
			int selection = JOptionPane.showOptionDialog(frame, message, "Dialogue Pass/Fail", 0, 2, null, options, options[0]);
			if (selection == 0) {	    	
				Reporter.log("The dialogue window has Selected Pass");
				assertEquals(true, selection == 0,"In dialogue window the result is not selected as Pass ");
				Reporter.log("The dialogue window has Selected Pass");
			}else if (selection == 1) {	    	
				Reporter.log("The dialogue window has Selected Fail");
				assertEquals(true, selection == 1,"In dialogue window the result is not selected as Fail ");
				Reporter.log("The dialogue window has Selected Fail");
			}	   
		}else if (option =="OK") {
			String[] options = {"OK"};
			RemoteWebDriver.builder();
			int selection = JOptionPane.showOptionDialog(frame, message, "Dialogue OK", 0, 2, null, options, options[0]);
			if (selection == 0) {	    	
				Reporter.log("The dialogue window has selected OK");	    
				assertEquals(true, selection == 0,"In dialogue window OK is not selected ");
				Reporter.log("The dialogue window has selected OK");		
			}}}

	//entering catcha
	public void socialAccAuthn(String option) throws InterruptedException {		
		if(option =="Google") {		
			JFrame frameCaptcha = new JFrame();
			frameCaptcha.setAlwaysOnTop(true);
			//JTextField fieldCaptcha = new JTextField();
			String valueCaptcha = JOptionPane.showInputDialog(frameCaptcha, "Please enter the captcha value.\n     Then click OK.", "Input-Captcha", JOptionPane.WARNING_MESSAGE);			
			driver.findElement(By.name("ca")).sendKeys(valueCaptcha);
			Thread.sleep(1000);
		}else if(option == "Apple") {
			JFrame frameCaptcha = new JFrame();
			frameCaptcha.setAlwaysOnTop(true);		
			String valueCaptcha = JOptionPane.showInputDialog(frameCaptcha, "Please enter the Authentication value. \nThen click OK.", "Input-Captcha", JOptionPane.WARNING_MESSAGE);			
			driver.findElement(By.id("char0")).sendKeys(valueCaptcha);			
			Thread.sleep(2000);
			Reporter.log("Aunthencation for Apple Entered");
		
		}
	}




	public void isPresent(String locator, String element) {		
		List<WebElement> txt = null;
		if(locator=="xpath") {				
			txt= driver.findElements(By.xpath(element));
		}else if (locator=="id") {
			txt= driver.findElements(By.id(element));
		}else 	if(locator=="name") {
			txt= driver.findElements(By.name(element));
		}else 	if(locator=="cssSelector") {
			txt= driver.findElements(By.cssSelector(element));
		}else 	if(locator=="linkText") {
			txt= driver.findElements(By.linkText(element));
		} else 	if(locator=="partialLinkText") {
			txt= driver.findElements(By.partialLinkText(element));
		}else 	if(locator=="className") {
			txt= driver.findElements(By.className(element));
		}		
		assertEquals(true,txt.size()>0);	    	  	    	  
		Reporter.log("Element is present. "+txt.size());
		System.out.println("this is the size "+txt.size());
	}


	public void isNotPresent(String locator, String element) {		
		List<WebElement> txt = null;
		if(locator=="xpath") {				
			txt= driver.findElements(By.xpath(element));
		}else if (locator=="id") {
			txt= driver.findElements(By.id(element));
		}else 	if(locator=="name") {
			txt= driver.findElements(By.name(element));
		}else 	if(locator=="cssSelector") {
			txt= driver.findElements(By.cssSelector(element));
		}else 	if(locator=="linkText") {
			txt= driver.findElements(By.linkText(element));
		} else 	if(locator=="partialLinkText") {
			txt= driver.findElements(By.partialLinkText(element));
		}else 	if(locator=="className") {
			txt= driver.findElements(By.className(element));
		}	
		assertEquals(false,txt.size()>0);	    	  	    	  
		Reporter.log("Element is not present. "+txt.size());	    	  
		System.out.println("Element is not present. "+txt.size());
		System.out.println("this is the size "+txt.size());
	}






	//to perform Scroll on application using Selenium
	public void scrollWebPage() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(4000);	
	}

	//	//to perform scroll under an element
	//	public void scrollElement() {
	//		 EventFiringWebDriver e = new EventFiringWebDriver(driver);
	//	}



	//assertion to compare two strings
	public void assertion(String expected,  String actual) {		
		assertEquals(expected, actual);
	}

	//assertion to compare boolean true or false
	public void assertionBoolean(boolean b, boolean c) {		
		assertEquals(b, c);
	}

	//assign something to a variable
	public String assignVariable(String txt) {
		return ("String text = "+txt+";");
	}



	//refresh the webpage
	public void refresh() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);	

	}

	//minimize and maximize the webpage
	public void windowMaxMin(String action) throws InterruptedException {
		if(action=="minimize") {
			driver.manage().window().minimize();
		}else if (action=="maximize") {
			driver.manage().window().maximize();
		}		
		Thread.sleep(1000);		
	}


	//select an option from drop down
	public void selectOption(String locator, String element, String option) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement ele = null;
		if(locator=="xpath") {
			ele =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		}else if (locator =="id") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
		}else 	if(locator =="name") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
		}else 	if(locator =="cssSelector") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
		}else 	if(locator =="linkText") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element)));
		} else 	if(locator =="partialLinkText") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element)));
		} else 	if(locator =="className") {
			ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element)));
		}

		Thread.sleep(2000);
		Select sel = new Select(ele);
		sel.selectByVisibleText(option);

	}
	//creating random email id
			public void randomEmail(String varName) throws IOException, InterruptedException {
				String email_prefix = "soho_swdvt_";			
				String randomString = RandomStringUtils.randomAlphabetic(1) +
						RandomStringUtils.random(1, "aeiou") +
						RandomStringUtils.randomAlphabetic(3) +
						RandomStringUtils.randomAlphabetic(1) +
						RandomStringUtils.random(1, "aeiou") +
						RandomStringUtils.randomAlphabetic(3);
				email_prefix =email_prefix + randomString.toLowerCase();
				String filePath = System.getProperty("user.dir");				
				File f = new File(filePath+"\\variables\\"+execID);
				filePath=filePath+"\\variables\\"+execID+"\\";		
				f = new File(filePath+varName+".txt");
				if(f.createNewFile()==true) {					
					filePath=filePath+varName+".txt";
				}
				//filePath = filePath+"\\variables\\"+execID+"\\"+varName+".txt";							
				FileWriter fw=new FileWriter(filePath); 	
				fw.write(email_prefix+"@mail7.io"); 			
				fw.close();
				Reporter.log("Created a temporary Email " +email_prefix);
				if(driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).isDisplayed()) {
					driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
					Thread.sleep(1000);
					Reporter.log("Cookies pop window is present and clicked");
				}else {
					Reporter.log("Cookies pop window is not present");
				}
			
			}
			
			//get verification code
			public void verificationCode(String varName) throws InterruptedException, FileNotFoundException {
				//getting verification code
						String mainWindowHandle = driver.getWindowHandle();
						driver.switchTo().newWindow(WindowType.TAB);		
						driver.get("https://www.mail7.io/");
						Thread.sleep(1000);
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//*[@name='username'])[2]")));
						Thread.sleep(500);
						//z.scrollToElement("xpath", "(//*[@name='username'])[2]");
						String filePath = System.getProperty("user.dir");			
						filePath = filePath+"\\variables\\"+execID+"\\"+varName+".txt";
						//filePath = filePath+"\\variables\\testing\\"+varName+".txt";			
						File file = new File(filePath);		            
						Scanner sc = new Scanner(file);
						sc.useDelimiter("\\Z");
						String value= sc.next();
						driver.findElement(By.xpath("(//*[@name='username'])[2]")).sendKeys(value.replaceAll("@mail7.io", ""));
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
						Reporter.log("Entered the Email Verification code");
			}


	//	//check available options
	//	public void availableOptions() {
	//		   Select s = new Select(driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[3]/div[2]/div/div/div[1]")));
	//		      // getting the list of options in the dropdown with getOptions()
	//		      List <WebElement> op = s.getOptions();
	//		      int size = op.size();
	//		      for(int i =0; i<size ; i++){
	//		         String options = op.get(i).getText();
	//		         System.out.println(options);
	//		      }
	//	}


	//go to next tab
	public void nextTab(String url, String title) {
		//Get handles of the windows
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		java.util.Iterator<String> iterator = allWindowHandles.iterator();

		// Here we will check if child window has other child windows and will fetch the heading of the child window
		while (iterator.hasNext()) {
			String ChildWindow = iterator.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				//WebElement text = driver.findElement(By.id("sampleHeading"));
				Reporter.log("This is the URL " + driver.getCurrentUrl());
				Reporter.log("This is the title " + driver.getTitle());
				assertEquals(url, driver.getCurrentUrl());
				Reporter.log("Actual URL is "+ url+" compared with expected URL "+driver.getCurrentUrl());
				assertEquals(title, driver.getTitle());
				Reporter.log("Actual Title is "+ title+" compared with expected Title "+driver.getTitle());				
				driver.close();
			}
		}
		driver.switchTo().window(mainWindowHandle);
	}

	//add delay between steps
	public void delay(int time) throws InterruptedException {
		Thread.sleep(time);
	}

	//double click on an element	
	public void doubleClick(String locator, String element) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement ele=null;
		if(locator=="xpath") {
			ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		}else if (locator =="id") {
			ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
		}else 	if(locator =="name") {
			ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
		}else 	if(locator =="cssSelector") {
			ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
		}else 	if(locator =="linkText") {
			ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element)));
		} else 	if(locator =="partialLinkText") {
			ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(element)));
		} else 	if(locator =="className") {
			ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element)));
		}

		Actions act = new Actions(driver);
		act.moveToElement(ele).doubleClick().perform();	
		Thread.sleep(2000);
	}
	//navigate back to previous page on browser
	public void navigateBack() throws InterruptedException {
		driver.navigate().back();
		Thread.sleep(2000);
	}

	//check elelment
	public void checkElement(String locator, String element, String check) {
		List<WebElement> txt = null;
		WebElement ele = null;
		if(check.equals("isPresent") ||check.equals("isNotPresent")) {
			if(locator=="xpath") {	
				txt= driver.findElements(By.xpath(element));			
				System.out.println("this is the size at top level "+txt.size());						
			}else if (locator=="id") {
				txt= driver.findElements(By.id(element));				
			}else 	if(locator=="name") {
				txt= driver.findElements(By.name(element));				
			}else 	if(locator=="cssSelector") {
				txt= driver.findElements(By.cssSelector(element));			
			}else 	if(locator=="linkText") {
				txt= driver.findElements(By.linkText(element));			
			} else 	if(locator=="partialLinkText") {
				txt= driver.findElements(By.partialLinkText(element));				
			}else 	if(locator=="className") {
				txt= driver.findElements(By.className(element));				
			}
		}else if(check.equals("isEnabled") || check.equals("isNotEnabled")) {
			if(locator=="xpath") {				
				ele = driver.findElement(By.xpath(element));							
			}else if (locator=="id") {			
				ele = driver.findElement(By.id(element));
			}else 	if(locator=="name") {				
				ele = driver.findElement(By.name(element));
			}else 	if(locator=="cssSelector") {				
				ele = driver.findElement(By.cssSelector(element));
			}else 	if(locator=="linkText") {				
				ele = driver.findElement(By.linkText(element));
			} else 	if(locator=="partialLinkText") {				
				ele = driver.findElement(By.partialLinkText(element));
			}else 	if(locator=="className") {				
				ele = driver.findElement(By.className(element));
			}
		}			
		if(check.equals("isPresent")) {
			assertEquals(true,txt.size()>0);
			Reporter.log("Element is present. "+txt.size());
			System.out.println("this is the size "+txt.size());	
		}else if (check.equals("isNotPresent")) {			
			assertEquals(false,txt.size()>0);	    	  	    	  
			Reporter.log("Element is not present. "+txt.size());				
		}else if (check.equals("isEnabled")) {
			assertEquals(true,(ele.isEnabled()));
			Reporter.log("Element is Enabled. "+ele.isEnabled());			
		}else if (check.equals("isNotEnabled")){
			assertEquals(false,(ele.isEnabled()));
			Reporter.log("Element is Not Enabled. ");			
		}else if (check.equals("isSelected")) {
			assertEquals(true,(ele.isSelected()));
			Reporter.log("Element is Selected. "+(ele.isSelected()));						
		}		
	}


	public void clearCache(String browser) throws InterruptedException {
		String mainWindowHandle = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		if(browser.equals("chrome")) {				
			driver.get("chrome://settings/clearBrowserData");
			Thread.sleep(5000);
			JavascriptExecutor js = (JavascriptExecutor)driver;		
			String script = "return document.querySelector('settings-ui').shadowRoot.querySelector('settings-main').shadowRoot.querySelector('settings-basic-page').shadowRoot.querySelector('settings-section > settings-privacy-page').shadowRoot.querySelector('settings-clear-browsing-data-dialog').shadowRoot.querySelector('#clearBrowsingDataDialog').querySelector('#clearBrowsingDataConfirm')";
			WebElement cleardata = (WebElement)js.executeScript(script);		
			cleardata.click();		
		}else if(browser.equals("Edge")) {
			driver.get("edge://settings/clearBrowserData");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='clear-now']")).click();			
		}

		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(mainWindowHandle);
	}
	public void getAndComparePage(String expectedTitle, String expectedURL, String option) {
		String text;
		if(expectedTitle.isEmpty()==false && expectedURL.isEmpty()) {
			text = driver.getTitle();
			if(option == "contains") {			
				assertEquals(true, text.contains(expectedTitle),"Actual Title "+text+" does not contain "+expectedTitle);
				Reporter.log("Actual Title is "+ text+" compared with expected text contain in Title "+expectedTitle);		
			}if(option == "not contains") {
				assertEquals(false, text.contains(expectedTitle),"Actual Title "+text+" contains "+expectedTitle);
				Reporter.log("Actual Title is "+ text+" compared with expected text not contain in Title "+expectedTitle);
			}if (option == "equal") {				
				assertEquals(expectedTitle, text,"Actual Title "+text+" is not same as "+expectedTitle);
				Reporter.log("Actual Title and "+ text+" expected Title "+expectedTitle+" are same");				
			}if(option == "not equal") {
				assertNotEquals(expectedTitle, text,"Actual Title "+text+" is same as "+expectedTitle);
				Reporter.log("Actual Title and "+ text+" expected Title "+expectedTitle+" are different");				
			}
			//softAssert.assertEquals(expectedTitle,text,"Actual Title "+text+"is not same as expected Title "+expectedTitle);		
		}else if(expectedURL.isEmpty()==false && expectedTitle.isEmpty()) {
			text = driver.getCurrentUrl();
			if(option == "contains") {			
				assertEquals(true, text.contains(expectedURL),"Actual URL "+text+" does not contain "+expectedURL);
				Reporter.log("Actual URL is "+ text+" compared with expected text contain in URL "+expectedURL);		
			}if(option == "not contains") {
				assertEquals(false, text.contains(expectedURL),"Actual URL "+text+" contains "+expectedURL);
				Reporter.log("Actual URL is "+ text+" compared with expected text not contain in URL "+expectedURL);
			}if (option == "equal") {				
				assertEquals(expectedURL, text,"Actual URL "+text+" is not same as "+expectedURL);
				Reporter.log("Actual URL and "+ text+" expected URL "+expectedURL+" are same");				
			}if(option == "not equal") {
				assertNotEquals(expectedURL, text,"Expected and Actual are same");
				Reporter.log("Actual URL and "+ text+" expected URL "+expectedURL+" are different");				
			}			
			//softAssert.assertEquals(expectedTitle,text,"Actual "+text+"is not same as expected "+expectedTitle);

		}else if (expectedTitle.isEmpty()==false && expectedURL.isEmpty()==false) {
			text = driver.getTitle();
			assertEquals(expectedTitle, text,"Actual Title"+text+"is not same as expected Title"+expectedTitle);
			Reporter.log("Actual Title is "+ expectedTitle+" compared with expected Title "+text);
			text = driver.getCurrentUrl();
			assertEquals(expectedURL, text,"Actual URL "+text+"is not same as expected URL "+expectedURL);
			Reporter.log("Actual URL is "+ expectedURL+" compared with expected URL "+text);
		}
	}

	public void baseWindow() {
		String originalWindow = driver.getWindowHandle();
		driver.switchTo().window(originalWindow);
	}
	
	//switch to new window
	public void switchWindow() {
	//Switch to new opened window 
	for(String winHandle : driver.getWindowHandles()){
		driver.switchTo().window(winHandle);
	}
	Reporter.log("Switched to the new opened window");
}
	//switch back to main window
	public void switchBackToMainWindow(String varName) throws FileNotFoundException{
		if (varName.startsWith("@")) {			
			String filePath = System.getProperty("user.dir");			
			filePath = filePath+"\\variables\\"+execID+"\\"+varName+".txt";
			//filePath = filePath+"\\variables\\testing\\"+text+".txt";			
			File file = new File(filePath);		            
			Scanner sc = new Scanner(file);
			sc.useDelimiter("\\Z");
			String value= sc.next();	
			driver.switchTo().window(value);
			Reporter.log("Switch back to the Main browser window");
		}else {
			Reporter.log("Cannot switch back to the Main browser window");
		}
	}

	//printerName from keyboard
	public void printerName() throws InterruptedException {					
		List<WebElement> l=driver.findElements(By.xpath("//span[normalize-space()='"+name+"']"));
		if (l.size()==0){
			//driver.navigate().forward();
			driver.findElement(By.xpath("//div[@class='page-select']")).click();
			driver.findElement(By.xpath("//div[@title='2']")).click();
			Thread.sleep(2000);
			WebElement e = driver.findElement(By.xpath("//span[normalize-space()='"+name+"']"));
			Thread.sleep(2000);
			e.click();
			Thread.sleep(3000);		
		}else{
			WebElement e = driver.findElement(By.xpath("//span[normalize-space()='"+name+"']"));				
			e.click();
		}
		Thread.sleep(2000);
	}




}
