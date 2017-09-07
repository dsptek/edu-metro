package com.syhan.edu.mongodb.basic;

import java.util.List;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class ConnectionTest {
	//
	public static void main(String[] args) throws Exception {
		// 방법1. 디폴트로 localhost의 27017포트로 접속한다.
		MongoClient mongoClient = new MongoClient();

		// 방법2. 서버 주소로 접속하기 (디폴트 포트번호(27017)가 사용된다)
//		MongoClient mongoClient = new MongoClient("localhost");

		// 방법3. 서버주소와 포트번호로 접속하기
//		MongoClient mongoClient = new MongoClient("localhost", "27017");

		// 방법4. 복제셋에 연결하기 원하는 경우 복제 서버들을 나열한다
//		MongoClient mongoClient = new MongoClient(Arrays.asList(
//		        new ServerAddress("localhsot", 27017),
//		        new ServerAddress("localhsot", 27018),
//		        new ServerAddress("localhsot", 27019)));
		
		List<String> names = mongoClient.getDatabaseNames();
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println("==============================");
		
		DB db = mongoClient.getDB("mydb");
		System.out.println(db);
	}
}
