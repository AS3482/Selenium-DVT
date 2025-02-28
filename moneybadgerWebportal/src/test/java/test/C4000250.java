package test;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.genericMethods;
import config.api_call;
import org.testng.annotations.Listeners;
import org.testng.ITestContext;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

@Listeners(listeners.listeners_sandbox.class)
public class
C4000250
extends baseTest{
	
	static WebDriver driver;
	int ordinalNum;
	String stepName;
	String stepTypeName;
	String stepID;
	String section;
	
	
	
	@Test
	public void 
test(ITestContext testContext)
throws InterruptedException, Exception {
	genericMethods z = new genericMethods(driver);
		
tcVersion ="0.50";
stepID = "4000084";
stepTypeName = "";
stepName = "SetUp_Launch Browser";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
api_call.startSetUp(execID, stepID,stepName);
stepID = "4100398";
stepTypeName = "Launch Browser";
stepName = "Launch Browser";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.setBrowser("chrome");
genericMethods zc = new genericMethods(driver_chrome);
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4100399";
stepTypeName = "Set Base URL";
stepName = "Set Base URL";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.getURL("https://zsbportal-stage.zebra.com/");
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4100400";
stepTypeName = "Click";
stepName = "Click on Sign In with your email";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//button[contains(text(),'Sign In with your email')]");
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4100468";
stepTypeName = "Set Text";
stepName = "Enter the username";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//input[@id='username']", "zebra01.swdvt@gmail.com");
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4100469";
stepTypeName = "Set Text";
stepName = "Enter the password";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//input[@id='password']", "NewP@ss98765");
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4100470";
stepTypeName = "Click";
stepName = "Click on Sign in Button";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//button[@id='submit_id']");
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4107935";
stepTypeName = "Compare Page Name";
stepName = "Check home page shows up";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.getAndComparePage("Zebra Small Office Home Office","https://zsbportal-stage.zebra.com/home", "contains");
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
testContext.setAttribute("stop_watch_setup",watch.elapsed(TimeUnit.SECONDS));
watch = Stopwatch.createStarted();
stepID = "4000250";
stepTypeName = "";
stepName = "TDMSTest_54798";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
api_call.startMain(execID);
stepID = "4101442";
stepTypeName = "Compare Page Name";
stepName = "Check home page shows up";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.getAndComparePage("Zebra Small Office Home Office","https://zsbportal-stage.zebra.com/home", "contains");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4101443";
stepTypeName = "Click";
stepName = "Click ";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//*[@data-testid='printer-settings-button']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4106609";
stepTypeName = "Get Text and Compare";
stepName = "Get Text and Compare";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.getTextCompare("xpath", "//*[@data-testid='page-title']", "Settings");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4102721";
stepTypeName = "Upload file ";
stepName = "Upload file ";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.uploadFile("xpath", "//*[@data-testid='upload-input']", "dog.GIF", "4000250");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4106685";
stepTypeName = "Set Delay";
stepName = "Set Delay";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.delay(10000);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4103847";
stepTypeName = "Click";
stepName = "Click";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//*[@data-testid='edit-workspace-remove-image-button']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108435";
stepTypeName = "Get Text and Compare";
stepName = "Get Text and Compare";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.getTextCompare("xpath", "//*[@data-testid='edit-workspace-name-initials']", "MFW");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108429";
stepTypeName = "Set Text";
stepName = "Set Text";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//*[@data-testid='input-workspace-name']/div/input[@type='text']", "Hello Workspace");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108469";
stepTypeName = "Click";
stepName = "Click";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//*[@data-testid='edit-workspace-save-button']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108431";
stepTypeName = "Get Text and Compare";
stepName = "Get Text and Compare";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.getTextCompare("xpath", "//*[@data-testid='edit-workspace-name-initials']", "HW");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108432";
stepTypeName = "Upload file ";
stepName = "Upload file ";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.uploadFile("xpath", "//*[@data-testid='upload-input']", "Test2.bmp", "4000250");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108436";
stepTypeName = "Upload file ";
stepName = "Upload file ";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.uploadFile("xpath", "//*[@data-testid='upload-input']", "car.jpg", "4000250");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108437";
stepTypeName = "Upload file ";
stepName = "Upload file ";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.uploadFile("xpath", "//*[@data-testid='upload-input']", "stylish-indian-flag-design_1394-725.jpg", "4000250");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108438";
stepTypeName = "Upload file ";
stepName = "Upload file ";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.uploadFile("xpath", "//*[@data-testid='upload-input']", "png-2757379_1280.png", "4000250");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108489";
stepTypeName = "Click";
stepName = "Click";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//*[@data-testid='edit-workspace-remove-image-button']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108440";
stepTypeName = "Set Text";
stepName = "Set Text";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//*[@data-testid='input-workspace-name']/div/input[@type='text']", "My First Workspace");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108470";
stepTypeName = "Click";
stepName = "Click";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//*[@data-testid='edit-workspace-save-button']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108442";
stepTypeName = "Get Text and Compare";
stepName = "Get Text and Compare";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.getTextCompare("xpath", "//*[@data-testid='edit-workspace-name-initials']", "MFW");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4107936";
stepTypeName = "Click";
stepName = "Click";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//*[@data-testid='user-menu']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4107937";
stepTypeName = "Click";
stepName = "Click";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//*[@data-testid='user-menu-account-settings']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
testContext.setAttribute("stop_watch_main",watch.elapsed(TimeUnit.SECONDS));
watch = Stopwatch.createStarted();
stepID = "4000085";
stepTypeName = "";
stepName = "CleanUp_Click";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
api_call.startCleanUp(execID, stepID,stepName);
stepID = "4108296";
stepTypeName = "Set Text";
stepName = "Set Text";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//div[@data-testid='first-name']/div/input[@type='text']", "SWDVT");
api_call.startCleanUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108295";
stepTypeName = "Set Text";
stepName = "Set Text";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//div[@data-testid='last-name']/div/input[@type='text']", "User");
api_call.startCleanUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4102727";
stepTypeName = "Click";
stepName = "Click ";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//*[@data-testid='user-menu-avatar']");
api_call.startCleanUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4100402";
stepTypeName = "Click";
stepName = "Click";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//*[@data-testid='user-menu-logout']");
api_call.startCleanUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4101004";
stepTypeName = "Clear browser cache";
stepName = "Clear browser cache";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.clearCache("chrome");
api_call.startCleanUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4100347";
stepTypeName = "Close Browser";
stepName = "Close Browser";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
zc.closeBrowser();
api_call.startCleanUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
testContext.setAttribute("stop_watch_cleanup",watch.elapsed(TimeUnit.SECONDS));
	}
	

}
