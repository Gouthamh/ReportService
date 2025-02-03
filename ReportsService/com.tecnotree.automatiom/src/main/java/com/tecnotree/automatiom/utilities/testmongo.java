package com.tecnotree.automatiom.utilities;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class testmongo {
	
	public void mongo() {
	
	String mongoURI="mongodb://DAP_IAT:DAP_IAT@172.20.21.212:27017,172.20.21.216:27017/DAP_IAT?replicaSet=repl";
	MongoClientURI uri = new MongoClientURI(mongoURI);
	MongoClient mongodb = new MongoClient(uri);
	MongoDatabase database = mongodb.getDatabase("DAP_IAT");
	MongoCollection<Document> coll = database.getCollection("NOTIFICATION_EMAIL_HISTORY");
	long count1= coll.count();
	System.out.println();
	
	}
	public static void main(String []args) {
		
		
	}

}
