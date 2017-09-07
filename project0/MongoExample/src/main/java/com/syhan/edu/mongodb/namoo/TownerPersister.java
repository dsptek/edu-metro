package com.syhan.edu.mongodb.namoo;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.syhan.edu.mongodb.namoo.entity.Towner;

public class TownerPersister {
	//
	private static final String TownerCollectionName = "towner";
	
	//--------------------------------------------------------------------------
	public TownerPersister(){
		// 
	}
	
	//--------------------------------------------------------------------------
	
	public String create(Towner towner) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection collection = db.getCollection(TownerCollectionName);
		
		BasicDBObject doc = createDocument(towner);
		collection.insert(doc);
		
		String townerId = doc.getObjectId("_id").toString();
		towner.setId(townerId);
		return townerId;
 	}

	public Towner retrieve(String townerId) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(TownerCollectionName);
		
		DBObject doc = coll.findOne(new ObjectId(townerId));
		return mapToTowner((BasicDBObject) doc);
	}

	public Towner retrieveByEmail(String email) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(TownerCollectionName);
		
		DBObject doc = coll.findOne(new BasicDBObject("email", email));
		return mapToTowner((BasicDBObject) doc); 
	}

	public void update(Towner towner) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(TownerCollectionName);
		
		DBObject doc = createDocument(towner);
		coll.update(new BasicDBObject("_id", new ObjectId(towner.getId())), doc);
	}

	public void delete(Towner towner) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(TownerCollectionName);
		
		coll.remove(new BasicDBObject("_id", new ObjectId(towner.getId())));
	}

	public boolean isExistEmail(String email) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(TownerCollectionName);
		
		Long count = coll.count(new BasicDBObject("email", email));
		return (count > 0);
	}
	
	public void clearAll() {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(TownerCollectionName);
		coll.drop();
	}
	
	//--------------------------------------------------------------------------
	private BasicDBObject createDocument(Towner towner) {
		// 
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", towner.getName());
		doc.put("email", towner.getEmail());
		doc.put("password", towner.getPassword());
		
		return doc;
	}

	private Towner mapToTowner(BasicDBObject doc) {
		// 
		if (doc == null) { 
			return null;
		}
		
		String id = doc.getObjectId("_id").toString();
		Towner towner = new Towner(id); 
		towner.setName((String)doc.get("name")); 
		towner.setEmail((String)doc.get("email")); 
		towner.setPassword((String)doc.get("password")); 
		return towner;
	}
	
	public static void main(String[] args) throws Exception {
		//
		TownerPersister townerPersister = new TownerPersister();
		Towner towner = new Towner("Hong gildong", "hong@nextree.co.kr", "1234");
		townerPersister.clearAll();
		
		String townerId = townerPersister.create(towner);
		
		Towner readOne = townerPersister.retrieve(townerId);
		System.out.println(readOne);
		
		Towner readByEmail = townerPersister.retrieveByEmail("hong@nextree.co.kr");
		System.out.println(readByEmail);
		
		readOne.setName("Gildong");
		townerPersister.update(readOne);
		readOne = townerPersister.retrieve(townerId);
		System.out.println(readOne);
		
		townerPersister.delete(readOne);
		System.out.println(townerPersister.retrieve(townerId));
	}
}