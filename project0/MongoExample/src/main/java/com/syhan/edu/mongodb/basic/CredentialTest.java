package com.syhan.edu.mongodb.basic;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class CredentialTest {
	public static void main(String[] args) throws Exception {
		//MongoClient client = new MongoClient();
		MongoCredential credential = MongoCredential.createScramSha1Credential("namoouser", "mydb",
				"pass1234".toCharArray());

		String server = "localhost";
		MongoClient client = new MongoClient(new ServerAddress(server), Arrays.asList(credential));

		DB db = client.getDB("mydb");
		DBCollection coll = db.getCollection("communities");
		System.out.println(coll.findOne());
	}
}

//use admin 
//db.createUser({user:"admin", pwd:"pass1234", roles:["root"]}) 
// 
//use mydb 
//db.createUser({user:"namoouser", pwd:"pass1234", roles:["dbOwner"]}) 
// 
//재시작 
//mongod --auth 
//show databases <-- error 
// 
//use admin 
//db.auth("admin","pass1234") 
//show databases
//
//mongo shell 에서 사용자 계정 생성 후 mongod --auth 로 서버를 재시작하는 예제입니다.