package nara.metro.da.mongo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import nara.metro.da.mongo.document.login.LoginTimeDoc;
import nara.metro.da.mongo.document.login.LoginUserDoc;
import nara.metro.da.mongo.springdata.LoginTimeMongoRepository;
import nara.metro.da.mongo.springdata.LoginUserMongoRepository;
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
	private LoginUserMongoRepository loginUserMongoRepository;

	@Autowired
	private LoginTimeMongoRepository loginTimeMongoRepository;

	@Override
	public void create(LoginUser loginUser) {
		//
		String id = loginUser.getId();
		if (loginUserMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("LoginUser document[ID:%s] already exist.", id));

		String username = loginUser.getUsername();
		String metroId = loginUser.getMetroId();
		if (StringUtil.isNotEmpty(username) &&
				(loginUserMongoRepository.findByMetroIdAndUsername(metroId, username) != null))
			throw new AlreadyExistsException(String.format("Login user[%s] already exists in metro[%s]", username, metroId));

		try {
			loginUserMongoRepository.insert(loginUser);
		} catch (DataIntegrityViolationException e) {
			throw new AlreadyExistsException(String.format("Login user[%s] already exists in metro[%s]", loginUser.getEmail(), metroId));
		}
	}

	@Override
	public LoginUser retrieve(String id) throws NoSuchElementException {
		//
		LoginUser loginUser = loginUserMongoRepository.findOne(id);
		if (loginUser == null)
			throw new NoSuchElementException(String.format("No login user[%s] to retrieve.", id));

		return loginUser;
	}

	@Override
	public LoginUser retrieveByMetroIdAndEmail(String metroId, String email) {
		//
		return loginUserMongoRepository.findByMetroIdAndEmail(metroId, email);
	}

	@Override
	public LoginUser retrieveByMetroIdAndUserName(String metroId, String username) {
		//
		return loginUserMongoRepository.findByMetroIdAndUsername(metroId, username);
	}

	@Override
	public List<LoginUser> retrieveByMetroId(String metroId, int offset, int limit) {
		//
		List<LoginUserDoc> loginUserDocs = loginUserMongoRepository.findByMetroId(metroId, new PageRequest(offset, limit));
		
		return loginUserDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	@Override
	public void update(LoginUser loginUser) {
		//
		if (!loginUserMongoRepository.exists(loginUser.getId())) throw new NonExistenceException(String.format("No login user[%s] to update.", loginUser.getId()));
		loginUserMongoRepository.save(loginUser);
	}

	@Override
	public void delete(LoginUser loginUser) {
		//
		loginUserMongoRepository.delete(loginUser.getId());
	}

	@Override
	public void createLoginTime(LoginTime loginTime) {
		//
		String id = loginTime.getId();
		if (loginTimeMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("Login time[%s] already exists.", id));

		loginTimeMongoRepository.insert(loginTime);
	}

	@Override
	public List<LoginTime> retrieveLoginTimeByCitizenId(String citizenId, int offset, int limit) {
		//
//		Query query = new Query();
//		query.addCriteria(Criteria.where("citizenId").is(citizenId));
//		
//		query.skip(offset);
//		query.limit(limit);
//		List<LoginTimeDoc> loginTimeDocs = mongoTemplate.find(query, LoginTimeDoc.class);
//		
//		return LoginTimeDoc.toDomains(loginTimeDocs);
		List<LoginTimeDoc> loginTimeDocs = loginTimeMongoRepository.findByCitizenId(citizenId, new PageRequest(offset, limit));
		
		return loginTimeDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	@Override
	public void deleteLoginTime(String id) {
		//
		loginTimeMongoRepository.delete(id);
	}

	@Override
	public void deleteLoginTimesByCitizenId(String citizenId) {
		//
		loginTimeMongoRepository.deleteByCitizenId(citizenId);
	}

}
