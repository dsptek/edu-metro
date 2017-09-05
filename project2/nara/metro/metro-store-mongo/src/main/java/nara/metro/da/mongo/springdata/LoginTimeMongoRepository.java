package nara.metro.da.mongo.springdata;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import nara.metro.da.mongo.document.login.LoginTimeDoc;
import nara.metro.domain.entity.LoginTime;

public interface LoginTimeMongoRepository extends MongoRepository<LoginTime, String> {
	//
	void deleteByCitizenId(String citizenId);

	List<LoginTimeDoc> findByCitizenId(String citizenId, PageRequest pageRequest);
    
}
