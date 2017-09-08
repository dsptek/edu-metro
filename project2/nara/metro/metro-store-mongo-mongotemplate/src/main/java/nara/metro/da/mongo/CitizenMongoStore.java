package nara.metro.da.mongo;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import nara.metro.da.mongo.document.citizen.CitizenDoc;
import nara.metro.da.mongo.document.citizen.DisqualifiedCitizenDoc;
import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.metro.domain.store.CitizenStore;
import nara.share.exception.store.AlreadyExistsException;

@Repository
public class CitizenMongoStore implements CitizenStore {
	//
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public String create(Citizen citizen) {
		//
        String id = citizen.getId();

        Query query = new Query(Criteria.where("id").is(id));
		CitizenDoc citizenDoc = mongoTemplate.findOne(query, CitizenDoc.class);
		if (citizenDoc != null) {
        	throw new AlreadyExistsException(String.format("Citizen document[ID:%s] already exist.", id));
        }
	
		mongoTemplate.save(CitizenDoc.toDocument(citizen));
		
		return id;
	}

	@Override
	public Citizen retrieve(String id) throws NoSuchElementException {
		//
		Query query = new Query(Criteria.where("id").is(id));
		
		CitizenDoc citizenDoc = mongoTemplate.findOne(query, CitizenDoc.class);
		
        if (citizenDoc == null) {
        	throw new NoSuchElementException(String.format("No citizen[%s] to retrieve.", id));
        }

        return citizenDoc.toDomain();
	}

	@Override
	public List<Citizen> retrieveByName(String metroId, String name) {
		//
		Query query = new Query(Criteria.where("metroId").is(metroId)
				.andOperator(Criteria.where("name").is(name)));
		
		List<CitizenDoc> citizenDocs = mongoTemplate.find(query, CitizenDoc.class);
		
		return citizenDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	@Override
	public Citizen retrieveByMetroEmail(String metroId, String email) {
		//
		Query query = new Query(Criteria.where("metroId").is(metroId)
				.andOperator(Criteria.where("email").is(email)));
		
		CitizenDoc citizenDoc = mongoTemplate.findOne(query, CitizenDoc.class);
		if (citizenDoc == null) {
			return null;
		}
		
		return citizenDoc.toDomain();
	}

	@Override
	public Citizen retrieveByMetroUsername(String metroId, String username) {
		//
		Query query = new Query(Criteria.where("metroId").is(metroId)
				.andOperator(Criteria.where("username").is(username)));
		
		CitizenDoc citizenDoc = mongoTemplate.findOne(query, CitizenDoc.class);
		if (citizenDoc == null) {
			return null;
		}
		
		return citizenDoc.toDomain();
	}

	@Override
	public List<Citizen> retrieveByEmail(String email) {
		//
		Query query = new Query(Criteria.where("email").is(email));
		
		List<CitizenDoc> citizenDocs = mongoTemplate.find(query, CitizenDoc.class);
		return citizenDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	@Override
	public List<Citizen> retrieveByMetro(String metroId, int offset, int limit) {
		//
		Query query = new Query();
		query.addCriteria(Criteria.where("metroId").is(metroId));
		
		query.skip(offset);
		query.limit(limit);
		List<CitizenDoc> citizenDocs = mongoTemplate.find(query, CitizenDoc.class);
		
		return CitizenDoc.toDomains(citizenDocs);
	}

	@Override
	public void update(Citizen citizen) {
		//
		String id = citizen.getId();
				
		Query query = new Query(Criteria.where("id").is(id));
		
		CitizenDoc citizenDoc = mongoTemplate.findOne(query, CitizenDoc.class);
		
		if (citizenDoc != null) {
        	throw new AlreadyExistsException(String.format("Citizen document[ID:%s] already exist.", id));
        }

		mongoTemplate.save(CitizenDoc.toDocument(citizen));
	}

	@Override
	public void delete(Citizen citizen) {
		//
		Query query = new Query(Criteria.where("id").is(citizen.getId()));
		
		mongoTemplate.remove(query, CitizenDoc.class);
	}

	@Override
	public void createDisqualified(DisqualifiedCitizen citizen) {
		//
//        String id = citizen.getId();
//        if (disqualifiedCitizenMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("DisqualifiedCitizen document[ID:%s] already exist.", id));
//
//        DisqualifiedCitizenDoc citizenDoc = DisqualifiedCitizenDoc.toDocument(citizen);
//        disqualifiedCitizenMongoRepository.save(citizenDoc);
	}

	@Override
	public DisqualifiedCitizen retrieveDisqualified(String id) throws NoSuchElementException {
		//
//		DisqualifiedCitizenDoc citizenDoc = disqualifiedCitizenMongoRepository.findOne(id);
//        if (citizenDoc == null) throw new NoSuchElementException(String.format("No citizen[%s] to retrieve.", id));
//
//        return citizenDoc.toDomain();
		return null;
	}

	@Override
	public List<DisqualifiedCitizen> retrieveDisqualifiedByMetro(String metroId) {
		//
//		List<DisqualifiedCitizenDoc> citizenDocs = disqualifiedCitizenMongoRepository.findByMetroId(metroId);
//        return DisqualifiedCitizenDoc.toDomains(citizenDocs);
		return Collections.emptyList();
	}

	@Override
	public void delete(DisqualifiedCitizen citizen) {
		//
//		disqualifiedCitizenMongoRepository.delete(citizen.getId());
	}

}
