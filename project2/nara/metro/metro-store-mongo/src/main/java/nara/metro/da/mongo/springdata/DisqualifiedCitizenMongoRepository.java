package nara.metro.da.mongo.springdata;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import nara.metro.da.mongo.document.citizen.DisqualifiedCitizenDoc;

public interface DisqualifiedCitizenMongoRepository extends MongoRepository<DisqualifiedCitizenDoc, String> {
	//
	List<DisqualifiedCitizenDoc> findByMetroId(String metroId);
}
