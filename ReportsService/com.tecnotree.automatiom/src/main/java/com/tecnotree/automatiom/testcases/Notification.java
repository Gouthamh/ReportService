//package com.tecnotree.automatiom.testcases;
//
//import java.io.IOException;
//
//import org.bson.Document;
//import org.testng.Assert;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Test;
//
//import com.mongodb.client.MongoCollection;
//import com.tecnotree.automatiom.Methods.Method;
//import com.tecnotree.automatiom.SS0_aaccess_token.ResponseFromSSO;
//import com.tecnotree.automatiom.routers.Routers;
//import com.tecnotree.automatiom.utilities.Fileupload;
//import com.tecnotree.automatiom.utilities.MongoConnections;
//
//import io.restassured.response.Response;
//
//public class Notification {
//	
//@BeforeSuite
//	public void Test001() throws InterruptedException  {
//		
//		MongoCollection<Document> NOTIFICATION_MASTER = MongoConnections.connect(Routers.mongoURL, Routers.databasename, "NOTIFICATION_MASTER");
//        // Assert document existence in NOTIFICATION_MASTER
//		Assert.assertEquals(MongoConnections.documentExists(NOTIFICATION_MASTER, "NOTIFICATION_ID_N", 2094), true);
//		Assert.assertEquals(MongoConnections.documentExists(NOTIFICATION_MASTER, "EMAIL_GW_ID", "7754"), true);
//        // Delete documents in NOTIFICATION_EMAIL_HISTORY with NOTIFICATION_ID 2094
//		MongoCollection<Document> NOTIFICATION_EMAIL_HISTORY = MongoConnections.connect(Routers.mongoURL, Routers.databasename, "NOTIFICATION_EMAIL_HISTORY");
//		MongoConnections.deleteManyDocument(NOTIFICATION_EMAIL_HISTORY, "NOTIFICATION_ID",2094);
//		
//		try {
//			Fileupload files= new Fileupload();
//			Response response;
//			try {
//				response = (Response) Method.Post(Fileupload.readFileContent("NoitifcationrequestPayload.json"),Routers.base_url,Routers.notification, Routers.triggerNotification,ResponseFromSSO.gettoken());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            // Assert response status code and payload
//			Assert.assertEquals(response.statusCode(), 200);
//			Assert.assertEquals(response.jsonPath().getString("responseNotificationPayload.EMAILDESC.RESPONSECODE").toString(), "SUCCESS");
//		} catch (IOException e) {  
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Thread.sleep(5000);
//        // Assert document existence in NOTIFICATION_EMAIL_HISTORY after triggering notification
//		Assert.assertEquals(MongoConnections.documentExists(NOTIFICATION_EMAIL_HISTORY, "NOTIFICATION_ID", 2094), true);
//		MongoConnections.closeConnection();
//		System.out.println("-----------------------------NotificationClass------------------------------------------");
//
//
//	}
//
//}
