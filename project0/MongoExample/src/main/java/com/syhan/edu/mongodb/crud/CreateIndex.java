package com.syhan.edu.mongodb.crud;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class CreateIndex {
	public static void main(String[] args) throws Exception {
		//
		MongoClient client = new MongoClient();
		DB db = client.getDB("mydb");
		DBCollection coll = db.getCollection("communities");
		
		//
		coll.createIndex(new BasicDBObject("name", 1));
		
		//
		List<DBObject> indexes = coll.getIndexInfo();

		for (DBObject index : indexes) {
		    System.out.println(index);
		}

	}
}
