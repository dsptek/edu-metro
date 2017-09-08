package nara.metro.da.mongo;

import java.util.ArrayList;
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
import nara.metro.domain.entity.Metro;
import nara.metro.domain.entity.MetroBook;
import nara.metro.domain.store.MetroStore;
import nara.share.exception.store.AlreadyExistsException;

@Repository
public class MetroMongoStore implements MetroStore {
	//
	private static final String METRO_BOOK_ID = "METRO";
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public String create(Metro metro) {
		//
		String id = metro.getId();
		
		Query query = new Query();
		query.addCriteria(
				Criteria.where("id").is(id));
		
		MetroDoc metroDoc = mongoTemplate.findOne(query, MetroDoc.class);
		if (metroDoc != null) {
			throw new AlreadyExistsException(String.format("Metro document[ID:%s] already exist.", id));
		}
		
		mongoTemplate.insert(MetroDoc.toDocument(metro));
		
		return id;
	}

	@Override
	public Metro retrieve(String id) throws NoSuchElementException {
		//
		Query query = new Query();
		query.addCriteria(
				Criteria.where("id").is(id));
		
		MetroDoc metroDoc = mongoTemplate.findOne(query, MetroDoc.class);
		
        if (metroDoc == null) {
        	throw new NoSuchElementException(String.format("No Metro[%s] to retrieve.", id));
        }

        return metroDoc.toDomain();
	}

	@Override
	public Metro retrieveByName(String metroName) {
		//
		Query query = new Query();
		query.addCriteria(
				Criteria.where("name").is(metroName));
		
		MetroDoc metroDoc = mongoTemplate.findOne(query, MetroDoc.class);
		
		if (metroDoc == null) {
			return null;
		}
		
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
		List<MetroDoc> metroDocs = mongoTemplate.findAll(MetroDoc.class);
		
		List<Metro> results = new ArrayList<Metro>();
		for (MetroDoc doc: metroDocs) {
			results.add(doc.toDomain());
		}
		
		return results;
	}

	@Override
	public void update(Metro metro) {
		//
		if (retrieve(metro.getId()) == null) {
			throw new NoSuchElementException(String.format("No Metro document[ID:%s] to update.", metro.getId()));
		}
		
		mongoTemplate.save(MetroDoc.toDocument(metro));
	}

	@Override
	public void delete(Metro metro) {
		//
		Query query = new Query();
		query.addCriteria(Criteria.where("metroId").is(metro.getId()));
		
		mongoTemplate.remove(query, MetroDoc.class);
	}

	@Override
	public boolean existByName(String name) {
		//
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		
		MetroDoc metroDoc = mongoTemplate.findOne(query, MetroDoc.class);
		if (metroDoc == null || metroDoc.getId() == null) {
			return false;
		}
		
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
		List<MetroBookDoc> docs = mongoTemplate.findAll(MetroBookDoc.class);
		
		if (docs.isEmpty()) {
			mongoTemplate.save(new MetroBookDoc(METRO_BOOK_ID));
		}
		
		return mongoTemplate.findById(METRO_BOOK_ID, MetroBookDoc.class).toDomain();
    }
	
	private void updateMetroBook(MetroBook metroBook) {
        //
		Update update = new Update();
		update.set("sequence", metroBook.getSequence());
		
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(METRO_BOOK_ID));
		
		mongoTemplate.updateFirst(query, update, MetroBookDoc.class);
    }
	
}
