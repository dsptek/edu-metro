package com.syhan.edu.mongodb.crud;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class CreateDocuments {
	//
	public static void main(String[] args) throws Exception {
		//
		MongoClient client = new MongoClient();
		DB db = client.getDB("mydb");
		DBCollection coll = db.getCollection("communities");
		
		// 문서의 생성
		BasicDBObject doc = new BasicDBObject("name", "MongoDB").
		        append("manager", "hyunohkim").
		        append("openDate", new Date());

		// 컬렉션에 문서 추가
		System.out.println("Count of documents before insert : " + coll.getCount());
		coll.insert(doc);

		// 컬렉션의 문서 개수
		System.out.println("Count of documents after insert : " + coll.getCount());

	}
}
