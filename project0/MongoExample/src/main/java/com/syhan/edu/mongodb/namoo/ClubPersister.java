package com.syhan.edu.mongodb.namoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.syhan.edu.mongodb.namoo.entity.Club;
import com.syhan.edu.mongodb.namoo.entity.ClubAdmin;
import com.syhan.edu.mongodb.namoo.entity.Towner;

public class ClubPersister {
	//
	private static final String ClubCollectionName = "club";
	
	//--------------------------------------------------------------------------
	public ClubPersister(){
		// 
	}

	public String create(Club club) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		BasicDBObject doc = createDocument(club);
		coll.insert(doc);
		
		String clubId = doc.getObjectId("_id").toString();
		club.setId(clubId);
		return clubId;
	}

	public Club retrieve(String clubId) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		DBObject doc = coll.findOne(new ObjectId(clubId));
		
		return mapToClub(doc);
	}

	public Club retrieveByName(String clubName) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		DBObject doc = coll.findOne(new BasicDBObject("name", clubName));
		
		return mapToClub(doc);
	}

	public List<Club> retrieveAll() {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		DBCursor cursor = null;
		List<Club> clubs = new ArrayList<Club>();
		try {
			cursor = coll.find();
			while (cursor.hasNext()) {
				clubs.add(mapToClub(cursor.next()));
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		
		return clubs;
	}

	public void update(Club club) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		DBObject doc = createDocument(club);
		coll.update(new BasicDBObject("_id", new ObjectId(club.getId())), doc);
	}

	public void delete(Club club) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		coll.remove(new BasicDBObject("_id", new ObjectId(club.getId())));
	}

	public boolean isExistClubByName(String clubName) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		Long count = coll.count(new BasicDBObject("name", clubName));
		return count > 0;
	}

	public void changeAdmin(String clubId, ClubAdmin admin) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		DBObject adminDoc = createDocument(admin);
		DBObject query = new BasicDBObject("_id", new ObjectId(clubId));
		DBObject updateDoc = new BasicDBObject(
				"$set", new BasicDBObject("admin", adminDoc));
		coll.update(query, updateDoc);
	}

	public ClubAdmin retrieveAdmin(String clubId) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		DBObject doc = coll.findOne(new ObjectId(clubId));
		
		return mapToClub(doc).getAdmin();
	}

	public List<Club> retrieveManagedClubs(String adminEmail) {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		
		DBCursor cursor = null;
		List<Club> clubs = new ArrayList<Club>();
		try {
			cursor = coll.find(new BasicDBObject("admin.email", adminEmail));
			while (cursor.hasNext()) {
				clubs.add(mapToClub((BasicDBObject) cursor.next()));
			}
		} finally {
			if (cursor != null) cursor.close();
		}
		return clubs;
	}
	
	public void clearAll() {
		//
		DB db = MongoClientFactory.getDB();
		DBCollection coll = db.getCollection(ClubCollectionName);
		coll.drop();
	}
	
	//--------------------------------------------------------------------------
	private BasicDBObject createDocument(Club club) {
		// 
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", club.getName());
		doc.put("description", club.getDescription());
		doc.put("openDate", club.getOpenDate());
		doc.put("admin", createDocument(club.getAdmin()));
		
		return doc;
	}
	
	private BasicDBObject createDocument(ClubAdmin clubAdmin) {
		//
		BasicDBObject doc = new BasicDBObject();
		doc.put("townerId", clubAdmin.getId());
		doc.put("name", clubAdmin.getName());
		doc.put("email", clubAdmin.getEmail());
		
		return doc;
	}
	
	private Club mapToClub(DBObject doc) {
		//
		if (doc == null) { 
			return null;
		}
		
		String clubId = ((BasicDBObject)doc).getObjectId("_id").toString();
		Club club = new Club(clubId);
		club.setName((String)doc.get("name"));
		club.setDescription((String)doc.get("description"));
		club.setOpenDate((Date)doc.get("openDate"));
		DBObject adminDoc = (DBObject)doc.get("admin");
		club.setAdmin(mapToAdmin(adminDoc));
		
		return club;
	}
	
	private ClubAdmin mapToAdmin(DBObject doc) {
		//
		if (doc == null) { 
			return null;
		}
		
		String townerId = (String)doc.get("townerId"); 
		Towner towner = new Towner(townerId);
		towner.setEmail((String)doc.get("email"));
		towner.setName((String)doc.get("name"));
		
		return new ClubAdmin(towner);
	}
	
	public static void main(String[] args) throws Exception {
		ClubPersister clubPersister = new ClubPersister();
		Towner towner = new Towner("Hong gildong", "hong@nextree.co.kr", "1234");
		ClubAdmin clubAdmin = new ClubAdmin(towner);
		
		clubPersister.clearAll();
		
		String clubId = clubPersister.create(new Club("Baseball club", "This is baseball club.", clubAdmin));
		clubPersister.create(new Club("Soccer club", "This is Soccer club.", clubAdmin));
		clubPersister.create(new Club("Basketball club", "This is basketball club.", clubAdmin));
		
		System.out.println(clubPersister.retrieve(clubId));
		
		List<Club> clubs = clubPersister.retrieveManagedClubs("hong@nextree.co.kr");
		for (Club club : clubs) {
			System.out.println(club);
		}
		
		clubPersister.changeAdmin(clubId, new ClubAdmin(new Towner("Kim dongsu", "kim@nextree.co.kr", "1234")));
		
		System.out.println("=============== after change admin ===============");
		System.out.println(clubPersister.retrieve(clubId));
	}

	
}