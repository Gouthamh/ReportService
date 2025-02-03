package com.tecnotree.automatiom.utilities;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.tecnotree.automatiom.routers.Routers;

import java.util.Iterator;

import org.bson.Document;

public class MongoConnections {

    private static MongoClient mongodb;
    private static MongoDatabase database;
	
	
    public static MongoCollection<Document> connect(String mongoUri, String databaseName, String collectionName) {
    	
    	try {
			if(isMongoClientConnected()) {
				System.out.println("is alread connected");
			}
			else {
			MongoClientURI uri = new MongoClientURI(mongoUri);
			mongodb = new MongoClient(uri);
			}
			database = mongodb.getDatabase(databaseName);
			//return database.getCollection(collectionName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return database.getCollection(collectionName); 
    	
    }

    public static void closeConnection() {
        if (mongodb != null) {
        
            mongodb.close();
        }
    }
    
    public static boolean isMongoClientConnected() {
        return mongodb != null;
    }

    
    public static Integer countDocuments(MongoCollection<Document> collection) throws InterruptedException {
    	delay();
        return  (int) collection.countDocuments();
    }

    public static void deleteOneDocument(MongoCollection<Document> collection, String fieldName, Object value) {
        collection.deleteOne(Filters.eq(fieldName, value));
    }
    
    public static void deleteManyDocument(MongoCollection<Document> collection, String fieldName, Object value) throws InterruptedException {
    	
        collection.deleteMany(Filters.eq(fieldName, value));
    }

    public static boolean documentExists(MongoCollection<Document> collection, String fieldName, Object value) throws InterruptedException {
        try (MongoCursor<Document> cursor = collection.find(Filters.eq(fieldName, value)).iterator()) {
            return cursor.hasNext();
        }
    }
    public static void delay() throws InterruptedException {
    	Thread.sleep(5000);
    }
    
 public static void fetch(MongoCollection<Document> collection) throws InterruptedException {
    	
	 FindIterable<Document> iterDoc = collection.find();
     Iterator it = iterDoc.iterator();
     while (it.hasNext()) {
    	 Object document = it.next();
         String jsonDocument = ((Document) document).toJson();
         System.out.println(jsonDocument);
         System.out.println();
     }
    }
 
 public static void main(String[] args) throws InterruptedException {
	 
		MongoCollection<Document> NOTIFICATION_EMAIL_HISTORY = MongoConnections.connect(Routers.mongoURL,Routers.databasename, "DAP_FILE_UPLOAD_CONFIG");
		//fetch(NOTIFICATION_EMAIL_HISTORY);
		System.out.println(countDocuments(NOTIFICATION_EMAIL_HISTORY));
		System.out.println(documentExists(NOTIFICATION_EMAIL_HISTORY,"serviceName","localHost22"));
	
}

}
