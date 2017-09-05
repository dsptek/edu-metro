package nara.metro.da.mongo.springdata;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import nara.metro.da.mongo.document.login.LoginUserDoc;
import nara.metro.domain.entity.LoginUser;

public interface LoginUserMongoRepository extends MongoRepository<LoginUser, String> {
	//
	LoginUser findByMetroIdAndEmail(String metroId, String email);

	LoginUser findByMetroIdAndUsername(String metroId, String username);

	List<LoginUserDoc> findByMetroId(String metroId, PageRequest pageRequest);

}
