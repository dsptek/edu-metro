package com.syhan.edu.mongodb.crud;

import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class ReadCollection {
	public static void main(String[] args) throws Exception {
		//
		MongoClient client = new MongoClient();
		DB db = client.getDB("mydb");
		
		Set<String> collectionNames = db.getCollectionNames();
		for (String collectionName : collectionNames) {
		    System.out.println(collectionName);
		}
		System.out.println("========================");
		
		//
		DBCollection coll = db.getCollection("communities");
		System.out.println(coll);
	}
}
