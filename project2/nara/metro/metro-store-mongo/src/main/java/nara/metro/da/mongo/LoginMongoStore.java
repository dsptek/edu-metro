package nara.metro.da.mongo;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import nara.metro.da.mongo.document.LoginUserDoc;
import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.store.LoginStore;

@Repository
public class LoginMongoStore implements LoginStore {
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void create(LoginUser loginUser) {
		LoginUserDoc doc = LoginUserDoc.createDocument(loginUser);
		mongoTemplate.save(doc);
	}

	@Override
	public LoginUser retrieve(String id) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginUser retrieveByMetroIdAndEmail(String metroId, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginUser retrieveByMetroIdAndUserName(String metroId, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoginUser> retrieveByMetroId(String metroId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(LoginUser loginUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(LoginUser loginUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createLoginTime(LoginTime loginTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LoginTime> retrieveLoginTimeByCitizenId(String citizenId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLoginTime(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLoginTimesByCitizenId(String citizenId) {
		// TODO Auto-generated method stub
		
	}
}
