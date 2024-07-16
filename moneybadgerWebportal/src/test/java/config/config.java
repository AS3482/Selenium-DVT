package config;

public class config {
//	public static String url = System.getProperty("url", "https://zsbportal-dev.zebra.com");
//	public static String username = System.getProperty("username", "AS3482");
//	public static String password = System.getProperty("password", "Sunset@@Libertyville2");
	
	public static String url = System.getProperty("url", "https://zsbportal-stage.zebra.com");	
	public static String username = System.getProperty("username", "zebraswtest2@hotmail.com");
	public static String password = System.getProperty("password", "Zebratest123?");
	
	public static String email = System.getProperty("email", "abeda.zebra@gmail.com");
	public static String emailPassword = System.getProperty("emailPassword", "Employment_2");
	public static String fileName = System.getProperty("fileName", "MyNew");
	public static String wrongFileName = System.getProperty("wrongFileName", "xyz");
	public static String operatorName = System.getProperty("operatorName", "AS3482");
	
	//api
	public static String execId = System.getProperty("execId", "");
	public static String CID = System.getProperty("CID", "C4000014");
	public static String printerName = System.getProperty("printerName", "ZSB-14");
	public static String api_url_updated = System.getProperty("api_url_updated","https://tdmsapi.zebra.lan/");
	
	
	//for selecting a particular node
	public static String networkName = System.getProperty("networkName", "node_1");
	public static String nodeName = System.getProperty("nodeName", "chrome_firmlab04");

	

}

