package config;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.json.JSONObject;
import org.testng.Reporter;

import io.restassured.response.Response;

public class api_call {

	public static String execID() throws UnknownHostException {
		String execID;
		InetAddress  ip = InetAddress.getLocalHost();	
		if(config.execId.isEmpty()) {
			JSONObject jsonObject = new JSONObject();
			System.out.println("this is empty "+config.execId.isEmpty());
			//Database Connection and creating an execution ID
			jsonObject = new JSONObject();
			jsonObject.put("stationName", ip.getHostAddress());
			jsonObject.put("operatorName", config.operatorName);
			jsonObject.put("loopCount", "1");
			jsonObject.put("duttype", "website");
			jsonObject.put("testDataSource", "Selenium_Tests");
			jsonObject.put("resultSchema", "INProgress");
			execID=  given()			
					.contentType("application/json")
					.and()
					.body(jsonObject.toString())
					.post(config.api_url_updated+"api/CommonExecution/NewExecution")   //hit the post end point
					.thenReturn().asString();
			
			System.out.println("this is the execution ID " +execID);
			System.out.println("this is the execution "+execID+" this is the case");
			Reporter.log("this is the execID from testcase "+execID);
		}else {
			execID = config.execId;
			System.out.println("this is the execID from command prompt "+execID +"and this is the execID from config  " +config.execId);
			Reporter.log("this is the execution id from command prompt  " + execID);
		}

		return(execID);
		
	}

	public static void deviceDetails(String execID) {
		// inserting the Device details	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("version", "1.0");
		jsonObject.put("hardwareID", 0);
		jsonObject.put("firmwareID", 0);
		jsonObject.put("hardwareName", config.printerName);
		jsonObject.put("firmwareName", "Testing");
		jsonObject.put("serialNumber", "");		
		jsonObject.put("communicationDetails","Network - NW: 172.30.3.3:9100");
		jsonObject.put("project", "ZSB");
		jsonObject.put("timings", "");
		jsonObject.put("dependencies", "");
		jsonObject.put("learnMode", "");
		jsonObject.put("basePath", "");
		given().contentType("application/json")
		.and()
		.body(jsonObject.toString())
		.put(config.api_url_updated+"api/ExecEngineDevice/Insert_Device");
		//Reporter.log("insert device details ");

	}

	public static void insertToolVersion(String execID) {
		// inserting the tool version this is an optional because its only updating the version of zcat
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("appName", "selenium");
		jsonObject.put("version", "1.0");
		given().contentType("application/json")
		.and()
		.body(jsonObject.toString())
		.put(config.api_url_updated+"api/CommonExecution/InsertToolVersion");
		//Reporter.log("insert tool version ");
	}
	
	
	public static void insertStep(String execID, int i, String stepID, String stepTypeName, String stepName, String status, int exec_time, String section) {
		//insert step	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);
		jsonObject.put("leftID", 1);
		jsonObject.put("type", section);
		jsonObject.put("setupNumber", 0);
		jsonObject.put("ordinalNumber", i);
		jsonObject.put("stepID", stepID);
		jsonObject.put("stepTypeName", stepTypeName);		
		jsonObject.put("stepName", stepName);
		jsonObject.put("result", status);
		jsonObject.put("executionTime",exec_time);
		jsonObject.put("duttime", 0);		
		String stepExecution=  given().contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.put(config.api_url_updated+"api/ExecEngineStep/Insert_Step")
				.thenReturn().asString(); //hit the put end
		//System.out.println("this is the insert step result "+stepExecution.getStatusLine());
		//Reporter.log("this is the insert step message "+stepExecution);
		//System.out.println("this is the insert step message "+stepExecution);
	}

	
	public static void insertStepDetails(String execID, String section,int i, String reportText, String errorMessage) {
	
		//insert step details	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);
		jsonObject.put("leftID", 1);
		jsonObject.put("type", section);
		jsonObject.put("setupNumber", 0);
		jsonObject.put("ordinalNumber", i);
		jsonObject.put("reportText", reportText);
		jsonObject.put("errorMessage", errorMessage);		
		String stepExecution=  given().contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.put(config.api_url_updated+"api/ExecEngineStep/Insert_StepDetails")
				.thenReturn().asString(); //hit the put end
		//System.out.println("this is the insert step result "+stepExecution.getStatusLine());
		//Reporter.log("this is the insert step message "+stepExecution);
		System.out.println("this is the insert step message "+stepExecution);
	}
	
	public static void startSetUp(String execID, String setupID,String setupName) {
		//insert step	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);
		jsonObject.put("leftID", 1);		
		jsonObject.put("setupNumber", 0);
		jsonObject.put("setupID", setupID);	
		jsonObject.put("setupName", setupName);		

		String stepExecution=  given().contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.put(config.api_url_updated+"api/ExecEngineSetup/StartSetup")
				.thenReturn().asString(); //hit the put end
		//System.out.println("this is the insert start setup result "+stepExecution.get));
		//Reporter.log("this is the setup message "+stepExecution);
		//System.out.println("this is the start setup message "+stepExecution);
	}

	public static void EndSetUp(String execID, long exectime) {			
		//End setup	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);
		jsonObject.put("leftID", 1);		
		jsonObject.put("setupNumber", 0);
		jsonObject.put("executionTime", exectime);					
		String stepExecution=  given().contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.put(config.api_url_updated+"api/ExecEngineSetup/EndSetup")
				.thenReturn().asString(); //hit the put end
		//System.out.println("this is the insert start setup result "+stepExecution.get));
		//Reporter.log("this is the setup message "+stepExecution);
		//System.out.println("this is the start setup message "+stepExecution);
	}

	public static void startMain(String execID) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);		
		jsonObject.put("leftId", 1);
		String mainexec=  given().contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.put(config.api_url_updated+"api/ExecEngineMain/StartMain")
				.thenReturn().asString(); //hit the put end
		//System.out.println("this is the start of main "+mainexec);
	}
	
	public static void endMain(String execID,long execTime) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);		
		jsonObject.put("leftId", 1);
		jsonObject.put("executionTime", execTime);

		String endmain=  given().contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.put(config.api_url_updated+"api/ExecEngineMain/EndMain")
				.thenReturn().asString(); //hit the put end
		System.out.println("this is the end of  main "+endmain);
	}

	public static void startCleanUp(String execID, String cleanupID,String cleanupName) {
		//start cleanup	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);
		jsonObject.put("leftID", 1);		
		jsonObject.put("cleanUpNumber", 0);
		jsonObject.put("cleanUpId", cleanupID);	
		jsonObject.put("cleanUpName", cleanupName);					
		String stepExecution=  given().contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.put(config.api_url_updated+"api/ExecEngineCleanup/StartCleanup")
				.thenReturn().asString(); //hit the put end
		//System.out.println("this is the insert start setup result "+stepExecution.get));
		//Reporter.log("this is the setup message "+stepExecution);
		//System.out.println("this is the start setup message "+stepExecution);
	}

	public static void EndCleanUp(String execID, long exectime) {			
		//End cleanup		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("executionId", execID);
		jsonObject.put("loopIndex", 1);
		jsonObject.put("leftID", 1);		
		jsonObject.put("cleanUpNumber", 0);
		jsonObject.put("executionTime", exectime);					
		String stepExecution=  given().contentType("application/json")
				.and()
				.body(jsonObject.toString())
				.put(config.api_url_updated+"api/ExecEngineCleanup/EndCleanup")
				.thenReturn().asString(); //hit the put end
		//System.out.println("this is the insert start setup result "+stepExecution.get));
		//Reporter.log("this is the setup message "+stepExecution);
		//System.out.println("this is the start setup message "+stepExecution);
	}
	
	//upload case file 
	public static void uploadCaseFile(String execID, String caseId) {
		String projectPath = System.getProperty("user.dir");				
		File source = new File(projectPath+"\\src\\test\\java\\test\\C"+caseId+".java");		
		Response resp= given()
				.multiPart("file",source,"multipart/for-data")				
				.and()				
				.queryParam("executionId", execID)	
				.queryParam("caseId",caseId)
				.queryParam("leftID", 1)
				.queryParam("fileName","C"+caseId)
				.post(config.api_url_updated+"api/FileSave/UploadCaseFile");				
		System.out.println("This is end of upload case file "+resp.getStatusLine());		
	}


}
