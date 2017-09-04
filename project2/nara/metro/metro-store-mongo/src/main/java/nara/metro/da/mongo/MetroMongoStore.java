package nara.metro.da.mongo;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import nara.metro.da.mongo.document.metro.MetroBookDoc;
import nara.metro.da.mongo.document.metro.MetroDoc;
import nara.metro.da.mongo.springdata.MetroBookMongoRepository;
import nara.metro.da.mongo.springdata.MetroMongoRepository;
import nara.metro.domain.entity.Metro;
import nara.metro.domain.entity.MetroBook;
import nara.metro.domain.store.MetroStore;
import nara.share.exception.store.AlreadyExistsException;

@Repository
public class MetroMongoStore implements MetroStore {
	//
	private static final String METRO_BOOK_ID = "METRO";
	
	@Autowired
	private MetroMongoRepository metroMongoRepository;
	
	@Autowired
	private MetroBookMongoRepository metroBookMongoRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	
	@Override
	public String create(Metro metro) {
		//
		String id = metro.getId();
		if (metroMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("Metro document[ID:%s] already exist.", id));
		
		MetroDoc metroDoc = MetroDoc.toDocument(metro);
		metroMongoRepository.save(metroDoc);
		
		return id;
	}

	@Override
	public Metro retrieve(String id) throws NoSuchElementException {
		//
		MetroDoc metroDoc = metroMongoRepository.findOne(id);
        if (metroDoc == null) throw new NoSuchElementException(String.format("No Metro[%s] to retrieve.", id));

        return metroDoc.toDomain();
	}

	@Override
	public Metro retrieveByName(String metroName) {
		//
		MetroDoc metroDoc = metroMongoRepository.findByName(metroName);
		if (metroDoc == null) return null;
		return metroDoc.toDomain();
	}

	@Override
	public Metro nextCitizenSequenceMetro(String id) throws NoSuchElementException {
		//
		Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.inc("citizenSequence", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);

        MetroDoc metroDoc = mongoTemplate.findAndModify(query, update, options, MetroDoc.class);
        return metroDoc.toDomain();
	}

	@Override
	public List<Metro> retrieveAll() {
		//
		List<MetroDoc> metroDocs = metroMongoRepository.findAll();
		return MetroDoc.toDomains(metroDocs);
	}

	@Override
	public void update(Metro metro) {
		//
		if (!metroMongoRepository.exists(metro.getId())) throw new NoSuchElementException(String.format("No Metro document[ID:%s] to update.", metro.getId()));
		MetroDoc metroDoc = MetroDoc.toDocument(metro);
		metroMongoRepository.save(metroDoc);
	}

	@Override
	public void delete(Metro metro) {
		//
		metroMongoRepository.delete(metro.getId());
	}

	@Override
	public boolean existByName(String name) {
		//
        MetroDoc metroDoc = metroMongoRepository.findByName(name);
        if (metroDoc == null || metroDoc.getId() == null) return false;
        return true;
	}

	@Override
	public MetroBook nextSequenceBook() {
		//
		MetroBook metroBook = getMetroBook();
		metroBook.increaseSequence(1);
		updateMetroBook(metroBook);
		return metroBook;
	}

	@Override
	public MetroBook nextVolumeSequenceBook(int volume) {
		//
		MetroBook metroBook = getMetroBook();
        metroBook.increaseSequence(volume);
        updateMetroBook(metroBook);
        return metroBook;
	}
	
	 private MetroBook getMetroBook() {
		//
		if (!metroBookMongoRepository.exists(METRO_BOOK_ID)) {
			metroBookMongoRepository.insert(MetroBookDoc.toDocument(new MetroBook(METRO_BOOK_ID)));
		}
		return metroBookMongoRepository.findOne(METRO_BOOK_ID).toDomain();
	}
	 
	private void updateMetroBook(MetroBook metroBook) {
		//
		if (!metroBookMongoRepository.exists(metroBook.getId())) throw new NoSuchElementException(
					String.format("No MetroBook document[ID:%s] to update.", metroBook.getId()));
		MetroBookDoc metroBookDoc = MetroBookDoc.toDocument(metroBook);
		metroBookMongoRepository.save(metroBookDoc);
	}
	
}
