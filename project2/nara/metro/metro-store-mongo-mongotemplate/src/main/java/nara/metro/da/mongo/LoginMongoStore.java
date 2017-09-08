package nara.metro.da.mongo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import nara.metro.da.mongo.document.login.LoginTimeDoc;
import nara.metro.da.mongo.document.login.LoginUserDoc;
import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.store.LoginStore;
import nara.share.exception.store.AlreadyExistsException;
import nara.share.exception.store.NonExistenceException;
import nara.share.util.string.StringUtil;

@Repository
public class LoginMongoStore implements LoginStore {
	//
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void create(LoginUser loginUser) {
		//
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> using MongoTemplate");
		String id = loginUser.getId();
		
		Query query = new Query().addCriteria(Criteria.where("id").is(id));
		LoginUserDoc foundLoginUserDoc = mongoTemplate.findOne(query, LoginUserDoc.class);
		if (foundLoginUserDoc != null) {
			throw new AlreadyExistsException(String.format("LoginUser document[ID:%s] already exist.", id));
		}

		String username = loginUser.getUsername();
		String metroId = loginUser.getMetroId();
		if (StringUtil.isNotEmpty(username) &&
				(retrieveByMetroIdAndUserName(metroId, username) != null))
			throw new AlreadyExistsException(String.format("Login user[%s] already exists in metro[%s]", username, metroId));

		try {
			mongoTemplate.insert(LoginUserDoc.toDocument(loginUser));
		} catch (DataIntegrityViolationException e) {
			throw new AlreadyExistsException(String.format("Login user[%s] already exists in metro[%s]", loginUser.getEmail(), metroId));
		}
	}
	
	@Override
	public LoginUser retrieve(String id) throws NoSuchElementException {
		//
		Query query = new Query().addCriteria(Criteria.where("id").is(id));
		LoginUserDoc foundLoginUserDoc = mongoTemplate.findOne(query, LoginUserDoc.class);
		if (foundLoginUserDoc == null)
			throw new NoSuchElementException(String.format("No login user[%s] to retrieve.", id));

		return foundLoginUserDoc.toDomain();
	}

	@Override
	public LoginUser retrieveByMetroIdAndEmail(String metroId, String email) {
		//
		Query query = new Query();
		query.addCriteria(
				Criteria.where("metroId").is(metroId)
				.andOperator(
						Criteria.where("email").is(email)));
		return mongoTemplate.findOne(query, LoginUserDoc.class).toDomain();
	}

	@Override
	public LoginUser retrieveByMetroIdAndUserName(String metroId, String username) {
		//
		Query query = new Query();
		query.addCriteria(
				Criteria.where("metroId").is(metroId)
				.andOperator(
						Criteria.where("username").is(username)));
		return mongoTemplate.findOne(query, LoginUserDoc.class).toDomain();
	}

	@Override
	public List<LoginUser> retrieveByMetroId(String metroId, int offset, int limit) {
		//
		Query query = new Query();
		query.addCriteria(
				Criteria.where("metroId").is(metroId));
		List<LoginUserDoc> loginUserDocs = mongoTemplate.find(query, LoginUserDoc.class);
		
		return loginUserDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	@Override
	public void update(LoginUser loginUser) {
		//
		if (retrieve(loginUser.getId()) == null) throw new NonExistenceException(String.format("No login user[%s] to update.", loginUser.getId()));
		mongoTemplate.save(LoginUserDoc.toDocument(loginUser));
	}

	@Override
	public void delete(LoginUser loginUser) {
		//
		mongoTemplate.remove(LoginUserDoc.toDocument(loginUser));
	}

	@Override
	public void createLoginTime(LoginTime loginTime) {
		//
		String id = loginTime.getId();
		Query query = new Query();
		query.addCriteria(
				Criteria.where("id").is(id));
		
		if (mongoTemplate.findOne(query, LoginTimeDoc.class) != null) throw new AlreadyExistsException(String.format("Login time[%s] already exists.", id));

		mongoTemplate.insert(LoginTimeDoc.toDocument(loginTime));
	}

	@Override
	public List<LoginTime> retrieveLoginTimeByCitizenId(String citizenId, int offset, int limit) {
		//
		Query query = new Query();
		query.addCriteria(Criteria.where("citizenId").is(citizenId));
		
		query.skip(offset);
		query.limit(limit);
		List<LoginTimeDoc> loginTimeDocs = mongoTemplate.find(query, LoginTimeDoc.class);
		
		return loginTimeDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	@Override
	public void deleteLoginTime(String id) {
		//
		Query query = new Query();
		query.addCriteria(
				Criteria.where("id").is(id));
		LoginTimeDoc foundLoginTimeDoc = mongoTemplate.findOne(query, LoginTimeDoc.class);
		mongoTemplate.remove(foundLoginTimeDoc);
	}

	@Override
	public void deleteLoginTimesByCitizenId(String citizenId) {
		//
		Query query = new Query();
		query.addCriteria(
				Criteria.where("citizenId").is(citizenId));
		mongoTemplate.remove(query, LoginTimeDoc.class);
	}

}
