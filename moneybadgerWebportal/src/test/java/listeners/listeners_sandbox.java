package listeners;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.restassured.response.Response;
import test.baseTest;
import config.api_call;
import config.config;


public class listeners_sandbox implements ITestListener, IExecutionListener {

	InetAddress ip;
	String hostname;
	String ip_add;
	String succ = null;

	//variables for inserting steps
	//	static String stepID;
	//	static String stepTypeName;		
	//	static String stepName;

	public String hostName() throws Exception {			
		ip = InetAddress.getLocalHost();
		hostname = ip.getHostName();  	
		System.out.println(ip.getHostName());	
		return(hostname);
	}

	public String hostAdress() throws Exception {
		ip = InetAddress.getLocalHost();		
		ip_add = ip.getHostAddress();		
		System.out.println(ip.getHostAddress());	
		return(ip_add);
	}
	
	public void screenshot(WebDriver driver)  {		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String TimeStamp = d.toString().replace(":", "_").replace(" ", "_");
		String path = System.getProperty("user.dir");
		path =path.replace("\\", "/");
        //The below method will save the screen shot in d drive with test method name 
           try {
				FileUtils.copyFile(scrFile, new File(path+"/screenshot/"+TimeStamp+".png"));
				System.out.println("***Placed screen shot in "+path+"/screenshot/"+TimeStamp+".png"+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	// On Test Passed, this will give the status, execution start time and end time, start day and end day
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			status(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	// On Test Failure, this will give the status, execution start time and end time, start day and end day
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub	
		try {
			status(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	// On Test Skipped, this will give the status, execution start time and end time, start day and end day
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			status(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void status(ITestResult result) throws Exception {
		//listener_data.data();
		String status = null;
		String msg = null;
		String execID = baseTest.execID;


		String methodName = result.getMethod().getMethodName();
		String className = result.getMethod().getConstructorOrMethod().getDeclaringClass().getCanonicalName();
		//		long exec_start = (result.getStartMillis()/1000);
		//		long exec_end = (result.getEndMillis()/1000);


		long exec_start = (result.getStartMillis());
		long exec_end = (result.getEndMillis());
		//result.getMethod().
		long exec_time = (exec_end-exec_start)/ 1000 % 60;	
		if (result.getStatus() == ITestResult.SUCCESS) {
			status = "Pass";			
			Reporter.log("test passed "+status);
		}else if (result.getStatus() == ITestResult.FAILURE) {
			status = "Fail";
			Reporter.log("test failed "+status);
			 if (null != result.getThrowable()) {
			      msg = result.getThrowable().getMessage();
			      Reporter.log("this is the exception for failure "+msg);
			    }			
			System.out.println("this is the step ID "+result.getTestContext().getAttribute("stepID"));
			System.out.println("this is the step type name "+result.getTestContext().getAttribute("stepTypeName"));
			System.out.println("this is the step name "+result.getTestContext().getAttribute("stepName"));
			System.out.println("this is the ordinal number "+result.getTestContext().getAttribute("ordinalNum"));
			int ordinalNum = (Integer) result.getTestContext().getAttribute("ordinalNum");
			String stepID = (String) result.getTestContext().getAttribute("stepID");
			String stepTypeName = (String) result.getTestContext().getAttribute("stepTypeName");
			String stepName = (String) result.getTestContext().getAttribute("stepName");
			String section = (String) result.getTestContext().getAttribute("section");
			System.out.println("this is the section "+section);
		
			api_call.insertStep(execID, ordinalNum, stepID,stepTypeName,stepName, status, 0,section);
			api_call.insertStepDetails(execID, section,ordinalNum, msg, "");			
			//api_call.insertStep(execID, ordinalNum+1, "0", "This is overall Result", "this is the result", msg.substring(0,35), 0);


		}else if(result.getStatus() == ITestResult.SKIP){
			status = "Skip";
			Reporter.log("test skipped "+status);
			if (null != result.getThrowable()) {
			      msg = result.getThrowable().getMessage();
			      Reporter.log("this is the exception for skip "+msg);
			    }
		}	

		
		System.out.println("this is the exec id in listners "+execID);
		Reporter.log("this is the exec id in listners "+execID);
		JSONObject jsonObject = new JSONObject();
//		api_call.insertStep(execID, 0, "0", "This is overall Result", "this is the result", status, 0);

		// inserting the Device details	
		api_call.deviceDetails(execID);


		// inserting the tool version this is an optional because its only updating the version of zcat
		api_call.insertToolVersion(execID);


		// inserting the cases hierarchy	
		jsonObject.put("executionId", execID);
		jsonObject.put("delim1", "|");
		jsonObject.put("delim2", ",");
		jsonObject.put("table", "0,"+className.substring(6)+","+className.substring(6)+",1,"+baseTest.tcVersion);
		given().contentType("application/json")
		.and()
		.body(jsonObject.toString())
		.put(config.api_url_updated+"api/AEMSCaseHierarchy/Initialize_CasesHierarchy");   //hit the put end point



		//Start Execution Loop
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);
		given().contentType("application/json")
		.and()
		.body(jsonObject.toString())
		.put(config.api_url_updated+"api/ExecutionLoop/StartExecutionLoop");   //hit the put end point
		
		
		//End setup		
		api_call.EndSetUp(execID, baseTest.watch.elapsed(TimeUnit.SECONDS));

		api_call.endMain(execID, baseTest.watch.elapsed(TimeUnit.SECONDS));
		api_call.EndCleanUp(execID, baseTest.watch.elapsed(TimeUnit.SECONDS));
		//Execution Status	
		jsonObject.put("executionId", execID);		
		jsonObject.put("loopIndex", 1);
		jsonObject.put("leftId", 1);
		jsonObject.put("result", status);
		jsonObject.put("executionTime", exec_time);
		jsonObject.put("reportText", "this is failed");
		jsonObject.put("errorMessage",msg);
		Response response_status = given().contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.post(config.api_url_updated+"api/ExecEngineCase/Insert_CaseResult");// hit the post end point
		System.out.println("this is the status Status: " +status +" and this is response code of the api "+ response_status.getStatusCode());





		
		//api_call.endMain(execID, (Long) result.getTestContext().getAttribute("stop_watch_main"));
		
		//end cleanup		
		//api_call.EndCleanUp(execID, (Long) result.getTestContext().getAttribute("stop_watch_cleanup"));


		//Ending the Execution Loop returns successful
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);
		given().contentType("application/json").and()
		.body(jsonObject.toString())
		.post(config.api_url_updated+"api/ExecutionLoop/EndExecutionLoop");   //hit the post end point
		
		//upload case file
		className = className.replace("test.C", "");
		api_call.uploadCaseFile(execID,className);

		//end Execution
		jsonObject.put("executionId", execID);
		Response end_execution=  given()			
				.contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.post(config.api_url_updated+"api/CommonExecution/EndExecution/"+execID);   //hit the post end point				
		System.out.println("Status: " + end_execution.getStatusCode());




	}	



}



