package com.tecnotree.automatiom.utilities;

import com.tecnotree.automatiom.routers.Routers;

public class Mongotest {
	
	public long mongotestcount(String CollectionName) throws InterruptedException {
		MongoDb mongoCount = new MongoDb();
        mongoCount.MongoDBChecker(Routers.mongoURL, Routers.databasename, CollectionName);
        long count2 = mongoCount.countRecordsWithNotificationId();
        //System.out.println("--" + count2);
        mongoCount.close();
        return count2;
	}

}
