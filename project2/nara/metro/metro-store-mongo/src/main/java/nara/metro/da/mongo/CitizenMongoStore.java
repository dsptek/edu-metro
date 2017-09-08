package nara.metro.da.mongo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import nara.metro.da.mongo.document.citizen.CitizenDoc;
import nara.metro.da.mongo.document.citizen.DisqualifiedCitizenDoc;
import nara.metro.da.mongo.springdata.CitizenMongoRepository;
import nara.metro.da.mongo.springdata.DisqualifiedCitizenMongoRepository;
import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.metro.domain.store.CitizenStore;
import nara.share.exception.store.AlreadyExistsException;

@Repository
public class CitizenMongoStore implements CitizenStore {
	//
	@Autowired
	private CitizenMongoRepository citizenMongoRepository;
	
	@Autowired
	private DisqualifiedCitizenMongoRepository disqualifiedCitizenMongoRepository;
	
	@Override
	public String create(Citizen citizen) {
		//
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> using MongoRepository");
		
        String id = citizen.getId();
        if (citizenMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("Citizen document[ID:%s] already exist.", id));

        CitizenDoc citizenDoc = CitizenDoc.toDocument(citizen);
        citizenMongoRepository.save(citizenDoc);

        return id;
	}

	@Override
	public Citizen retrieve(String id) throws NoSuchElementException {
		//
		CitizenDoc citizenDoc = citizenMongoRepository.findOne(id);
        if (citizenDoc == null) throw new NoSuchElementException(String.format("No citizen[%s] to retrieve.", id));

        return citizenDoc.toDomain();
	}

	@Override
	public List<Citizen> retrieveByName(String metroId, String name) {
		//
		List<CitizenDoc> citizenDocs = citizenMongoRepository.findByMetroIdAndName_DisplayName(metroId, name);
        return CitizenDoc.toDomains(citizenDocs);
	}

	@Override
	public Citizen retrieveByMetroEmail(String metroId, String email) {
		//
		CitizenDoc citizenDoc = citizenMongoRepository.findByMetroIdAndEmail(metroId, email);
		if (citizenDoc == null) return null;
		return citizenDoc.toDomain();
	}

	@Override
	public Citizen retrieveByMetroUsername(String metroId, String username) {
		CitizenDoc citizenDoc = citizenMongoRepository.findByMetroIdAndUsername(metroId, username);
		if (citizenDoc == null) return null;
		return citizenDoc.toDomain();
	}

	@Override
	public List<Citizen> retrieveByEmail(String email) {
		//
		List<CitizenDoc> citizenDocs = citizenMongoRepository.findByEmail(email);
		return CitizenDoc.toDomains(citizenDocs);
	}

	@Override
	public List<Citizen> retrieveByMetro(String metroId, int offset, int limit) {
		//
//		List<CitizenDoc> citizenDocs = citizenMongoRepository.findByMetroId(metroId,
//				new PageRequest(0, 10,));
		
//		Query query = new Query();
//		query.addCriteria(Criteria.where("metroId").is(metroId));
//		
//		query.skip(offset);
//		query.limit(limit);
//		List<CitizenDoc> citizenDocs = mongoTemplate.find(query, CitizenDoc.class);
//		
//		return CitizenDoc.toDomains(citizenDocs);
		List<CitizenDoc> citizenDocs = citizenMongoRepository.findByMetroId(metroId, new PageRequest(offset, limit));
		return citizenDocs.stream()
				.map(doc -> doc.toDomain())
				.collect(Collectors.toList());
	}

	@Override
	public void update(Citizen citizen) {
		//
		if (!citizenMongoRepository.exists(citizen.getId())) throw new NoSuchElementException(String.format("No citizen document[ID:%s] to update.", citizen.getId()));
        CitizenDoc citizenDoc = CitizenDoc.toDocument(citizen);
        citizenMongoRepository.save(citizenDoc);
	}

	@Override
	public void delete(Citizen citizen) {
		//
		citizenMongoRepository.delete(citizen.getId());
	}

	@Override
	public void createDisqualified(DisqualifiedCitizen citizen) {
		//
        String id = citizen.getId();
        if (disqualifiedCitizenMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("DisqualifiedCitizen document[ID:%s] already exist.", id));

        DisqualifiedCitizenDoc citizenDoc = DisqualifiedCitizenDoc.toDocument(citizen);
        disqualifiedCitizenMongoRepository.save(citizenDoc);
	}

	@Override
	public DisqualifiedCitizen retrieveDisqualified(String id) throws NoSuchElementException {
		//
		DisqualifiedCitizenDoc citizenDoc = disqualifiedCitizenMongoRepository.findOne(id);
        if (citizenDoc == null) throw new NoSuchElementException(String.format("No citizen[%s] to retrieve.", id));

        return citizenDoc.toDomain();
	}

	@Override
	public List<DisqualifiedCitizen> retrieveDisqualifiedByMetro(String metroId) {
		//
		List<DisqualifiedCitizenDoc> citizenDocs = disqualifiedCitizenMongoRepository.findByMetroId(metroId);
        return DisqualifiedCitizenDoc.toDomains(citizenDocs);
	}

	@Override
	public void delete(DisqualifiedCitizen citizen) {
		//
		disqualifiedCitizenMongoRepository.delete(citizen.getId());
	}

}
