package test;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import config.config;

public class api_test {
	String execID;
	@Test
	public void test() {
	JSONObject jsonObject = new JSONObject();
	System.out.println("this is empty "+config.execId.isEmpty());
	//Database Connection and creating an execution ID
	jsonObject = new JSONObject();
	jsonObject.put("stationName", "172.30.4.5");
	jsonObject.put("operatorName", config.operatorName);
	jsonObject.put("loopCount", "1");
	jsonObject.put("duttype", "website");
	jsonObject.put("testDataSource", "Selenium_Tests");
	jsonObject.put("resultSchema", "INProgress");
	execID=  given()			
			.contentType("application/json")
			.and()
			.body(jsonObject.toString())
			.post("https://tdmsapi.zebra.lan/api/CommonExecution/NewExecution")   //hit the post end point
			.thenReturn().asString();
	
	System.out.println("this is the execution ID " +execID);
	System.out.println("this is the execution "+execID+" this is the case");
	Reporter.log("this is the execID from testcase "+execID);

}
}
