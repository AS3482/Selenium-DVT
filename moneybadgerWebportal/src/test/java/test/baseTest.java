package test;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.google.common.base.Stopwatch;

import config.api_call;

public class baseTest {
	WebDriver driver;
	public static String execID;
	public static Stopwatch watch;
	public static long stop_watch_setup;
	public static long stop_watch_cleanup;
	public static long stop_watch_main;
	public static String tcVersion;
	public static WebDriver driver_chrome;
	public static WebDriver driver_firefox;
	public static WebDriver driver_edge;
	public static WebDriver driver_safari;
	
	
	@BeforeTest
	public void execID(ITestContext testContext) throws UnknownHostException {
		execID = api_call.execID();
		watch = Stopwatch.createStarted();	
		
		//renaming variable folder created with case ID to exec ID
		String projectPath = System.getProperty("user.dir");		
		String className = testContext.getAllTestMethods()[0].getInstance().getClass().getName();
		className=className.replace("test.C", "");			
		File CIDFile = new File(projectPath+"\\variables\\"+className);		
		if(CIDFile.exists()) {
			CIDFile.renameTo(new File(projectPath+"\\variables\\"+execID));
		}
		
	}

@AfterTest
public void endTest() throws IOException {
	String projectPath = System.getProperty("user.dir");
	FileUtils.deleteDirectory(new File(projectPath+"\\DownloadFiles\\"+execID));		
	FileUtils.deleteDirectory(new File(projectPath+"\\variables\\"+execID));
	FileUtils.deleteDirectory(new File(projectPath+"\\files\\"+execID));
	FileUtils.deleteDirectory(new File(projectPath+"\\LDA_goldenImage\\"+execID));
	FileUtils.deleteDirectory(new File(projectPath+"\\LDA_imageCompare\\"+execID));	
	Reporter.log("Browser is closed");
}
}
