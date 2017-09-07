package com.syhan.edu.mongodb.crud;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ReadDocuments {
	public static void main(String[] args) throws Exception {
		//
		MongoClient client = new MongoClient();
		DB db = client.getDB("mydb");
		DBCollection coll = db.getCollection("communities");
		
		DBCursor cursor = coll.find();
		try {
		    while (cursor.hasNext()) {
		        DBObject document = cursor.next();
		        System.out.println(document);
		    }
		} finally {
		    cursor.close();
		}

	}
}
