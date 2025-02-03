package com.tecnotree.automatiom.utilities;


import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class MongoOperations extends MongoConnections {
	
	  public static Integer countDocuments(MongoCollection<Document> collection) {
	        return (int) collection.countDocuments();
	    }

	    public static void deleteOneDocument(MongoCollection<Document> collection, String fieldName, Object value) {
	        collection.deleteOne(Filters.eq(fieldName, value));
	    }
	    
	    public static void deleteManyDocument(MongoCollection<Document> collection, String fieldName, Object value) {
	        collection.deleteMany(Filters.eq(fieldName, value));
	    }

	    public static boolean documentExists(MongoCollection<Document> collection, String fieldName, Object value) {
	        try (MongoCursor<Document> cursor = collection.find(Filters.eq(fieldName, value)).iterator()) {
	            return cursor.hasNext();
	        }
	    }

}
