package nara.metro.da.mongo.springdata;

import org.springframework.data.mongodb.repository.MongoRepository;

import nara.metro.da.mongo.document.metro.MetroDoc;

public interface MetroMongoRepository extends MongoRepository<MetroDoc, String> {
	//
	MetroDoc findByName(String name);
    
}
