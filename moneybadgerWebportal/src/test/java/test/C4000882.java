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
C4000882
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
		
tcVersion ="0.09";
stepID = "4000732";
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
stepID = "4100205";
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
stepID = "4100411";
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
stepID = "4100412";
stepTypeName = "Click";
stepName = "Click Sign In with your email";
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
stepID = "4106890";
stepTypeName = "Set Text";
stepName = "Input username";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//input[@id='username']", "sohoswdvt1335.idc@outlook.com");
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4106555";
stepTypeName = "Set Text";
stepName = "input password";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//input[@id='password']", "BeMore!23456");
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4100166";
stepTypeName = "Click";
stepName = "Click Sign In";
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
stepID = "4103565";
stepTypeName = "Minimize and Maximize the webpage ";
stepName = "Minimize and Maximize the webpage ";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.windowMaxMin("maximize");
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4105575";
stepTypeName = "Set Delay";
stepName = "Set Delay";
ordinalNum = ordinalNum+1;
section = "Setup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.delay(3000);
api_call.startSetUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
testContext.setAttribute("stop_watch_setup",watch.elapsed(TimeUnit.SECONDS));
watch = Stopwatch.createStarted();
stepID = "4000882";
stepTypeName = "";
stepName = "TDMSTest_45220";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
api_call.startMain(execID);
stepID = "4101539";
stepTypeName = "Click";
stepName = "Click Design a Label";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//span[text()='Design a Label']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4101540";
stepTypeName = "Click";
stepName = "Click Create New Design";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//span[contains(text(),'Create New Design')]");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4101904";
stepTypeName = "Click";
stepName = "Click Media available on your current printers";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//div[contains(@data-testid,'printer-serial-number')][contains(text(),'Serial Number')]");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4101212";
stepTypeName = "Click";
stepName = "Click Continue";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//span[contains(text(),'Continue')]");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108025";
stepTypeName = "Give Name to a Label";
stepName = "Give Name to a Label";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.renameLabel("TestCase45220");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4106577";
stepTypeName = "Click";
stepName = "Click Connect Data File";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//div[text()='Connect Data File']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108023";
stepTypeName = "Click";
stepName = "Click Office365 contacts";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//span[text()='Office365 contacts']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4100455";
stepTypeName = "Switch to main frame";
stepName = "Switch to main frame";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.switchToMainFrame();
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108024";
stepTypeName = "Get text and save in variable";
stepName = "Get text and save in variable";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.getTextSaveInVaraible("windowHandle", "", "@OneDrive365");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108026";
stepTypeName = "Switch Browser ";
stepName = "Switch Browser ";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
//genericMethods zc = new genericMethods(driver_chrome);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108030";
stepTypeName = "Set Delay";
stepName = "Set Delay";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.delay(2000);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108028";
stepTypeName = "Set Text";
stepName = "Set Text OneDrive username";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//input[@name='loginfmt']", "xun27.pan@outlook.com");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108029";
stepTypeName = "Click";
stepName = "Click Next";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//input[@value='Next']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108031";
stepTypeName = "Set Text";
stepName = "Set Text OneDrive password";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.sendText("xpath", "//input[@placeholder='Password']", "Pst_3537175!!!");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108032";
stepTypeName = "Click";
stepName = "Click Sign In OneDrive";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//button[text()='Sign in']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4108033";
stepTypeName = "Click";
stepName = "Click Yes button";
ordinalNum = ordinalNum+1;
section = "Main";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//button[text()='Yes']");
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
testContext.setAttribute("stop_watch_main",watch.elapsed(TimeUnit.SECONDS));
watch = Stopwatch.createStarted();
stepID = "4000740";
stepTypeName = "";
stepName = "CleanUp_Close Browser";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
api_call.startCleanUp(execID, stepID,stepName);
stepID = "4106902";
stepTypeName = "Click";
stepName = "Click user-menu-avatar";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//div[contains(@data-testid,'user-menu-avatar')]");
api_call.startCleanUp(execID, stepID,stepName);
api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);
stepID = "4106604";
stepTypeName = "Click";
stepName = "Click";
ordinalNum = ordinalNum+1;
section = "Cleanup";
testContext.setAttribute("stepID", stepID);
testContext.setAttribute("stepTypeName", stepTypeName);
testContext.setAttribute("stepName", stepName);
testContext.setAttribute("ordinalNum", ordinalNum);
testContext.setAttribute("section", section);
z.Click("xpath", "//span[contains(text(),'Log Out')]");
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
