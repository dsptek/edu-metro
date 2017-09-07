package com.syhan.edu.mongodb.crud;

import com.mongodb.MongoClient;

public class ReadDatabasesAndDrop {
	public static void main(String[] args) throws Exception {
		//
		MongoClient mongoClient = new MongoClient();
		
		System.out.println("========== before ==========");		
		for (String dbName : mongoClient.getDatabaseNames()) {
		    System.out.println(dbName);
		}
		
		//
		mongoClient.dropDatabase("namoodb");
		
		System.out.println("========== after ==========");		
		for (String dbName : mongoClient.getDatabaseNames()) {
		    System.out.println(dbName);
		}
	}
}
