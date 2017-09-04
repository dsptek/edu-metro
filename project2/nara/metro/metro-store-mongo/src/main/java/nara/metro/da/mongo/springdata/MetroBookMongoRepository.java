package nara.metro.da.mongo.springdata;

import org.springframework.data.mongodb.repository.MongoRepository;

import nara.metro.da.mongo.document.metro.MetroBookDoc;

public interface MetroBookMongoRepository extends MongoRepository<MetroBookDoc, String> {
    //
}
