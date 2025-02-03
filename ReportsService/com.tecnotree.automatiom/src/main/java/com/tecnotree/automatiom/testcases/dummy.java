package com.tecnotree.automatiom.testcases;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tecnotree.automatiom.Methods.FormDataMultipleFile;
import com.tecnotree.automatiom.Methods.Method;
import com.tecnotree.automatiom.routers.Routers;
import com.tecnotree.automatiom.utilities.Fileupload;


public class dummy {
	
	RequestSpecification requestspecification;
	ResponseSpecification responsespecification;
	Fileupload files= new Fileupload();
	Method method;

	
//	@Test
//	public void Notification() throws IOException {
//    	String endpoints = Routers.notification_bulk_service+"/"+Routers.triggerBulkService;
//    	System.out.println(endpoints);
//    	Response response =(Response) method.Post(Fileupload.readFileContent("NoitifcationrequestPayload.json"),Routers.base_url,Routers.dapBulkProcess, endpoints);
//    	Assert.assertEquals(response.statusCode(), 200);
//    	Assert.assertEquals(response.jsonPath().getString("response").toString(), "notification-bulk-service triggered Successfully");
//    	
//	}
	@Test
	public void getmethod() {

		
		Method n = new Method();
		Method response1 = n.Get("https://dap.pe-lab3.bdc-rancher.tecnotree.com", "/dapBulkProcess", "/configure/fetchAllMasterConfiguratoins");
		System.out.println(response1.getStatusCode());
	}
//	@Test
//	public void putmethod() throws IOException {
//		Response response=method.Put(Fileupload.readFileContent("BulkConfigu.json"), Routers.base_url,Routers.dapBulkProcess,"/configure/UpdateBulkMasterConfig");
//		Assert.assertEquals(response.statusCode(), 200);
//		Assert.assertEquals(response.jsonPath().getString("msg").toString(), "Bulk master configuration successfuly updated into DB");
//			}
	
	@Test
	public void form_data() throws IOException{

    	 

Method m = new Method();
m.MultiPartData("https://dap.pe-lab3.bdc-rancher.tecnotree.com", "/notification", "/triggerNotificationForEmailAttachment", Fileupload.getFileList("").toString(), "{\r\n"
		+ "   \"notificationId\":37346,\r\n"
		+ "    \"msisdn\": \"9000000001\",\r\n"
		+ "    \"emailTo\": \"gouthamraj.kr@tecnotree.com\",\r\n"
		+ "    \"shortCode\": 8002,\r\n"
		+ "    \"systemId\": \"smppclient1\",   \r\n"
		+ "   \"inputValue\":{\r\n"
		+ "      \"amount\":8762348,\r\n"
		+ "      \"account_number\":53342876342867,\r\n"
		+ "      \"upi_id\":\"9986284402@upi\",\r\n"
		+ "      \"gmail\":\"gouthamrajkr@gmail.com\",\r\n"
		+ "      \"balance\":65234,\r\n"
		+ "      \"reference_number\":7653427,\r\n"
		+ "      \"phone_number\":9986284402\r\n"
		+ "   }\r\n"
		+ "}");
		
		
		
	}
	
	@Test
	public void form_dat111a() throws IOException{
		Response response = FormDataMultipleFile.MultiPartData111("https://dap.pe-lab3.bdc-rancher.tecnotree.com", "/notification", "/triggerNotificationForEmailAttachment", "{\r\n"
				+ "   \"notificationId\":37346,\r\n"
				+ "    \"msisdn\": \"9000000001\",\r\n"
				+ "    \"emailTo\": \"gouthamraj.kr@tecnotree.com\",\r\n"
				+ "    \"shortCode\": 8002,\r\n"
				+ "    \"systemId\": \"smppclient1\",   \r\n"
				+ "   \"inputValue\":{\r\n"
				+ "      \"amount\":8762348,\r\n"
				+ "      \"account_number\":53342876342867,\r\n"
				+ "      \"upi_id\":\"9986284402@upi\",\r\n"
				+ "      \"gmail\":\"gouthamrajkr@gmail.com\",\r\n"
				+ "      \"balance\":65234,\r\n"
				+ "      \"reference_number\":7653427,\r\n"
				+ "      \"phone_number\":9986284402\r\n"
				+ "   }\r\n"
				+ "}","Documents");
		
		System.out.println(response.statusCode());
		System.out.println(response.jsonPath());
		
		
		
	}

}
