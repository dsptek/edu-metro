package nara.metro.da.jpa;

import nara.metro.da.jpa.jpo.MetroBookJpo;
import nara.metro.da.jpa.jpo.MetroJpo;
import nara.metro.da.jpa.springdata.MetroBookRepository;
import nara.metro.da.jpa.springdata.MetroRepository;
import nara.metro.domain.entity.Metro;
import nara.metro.domain.entity.MetroBook;
import nara.metro.domain.store.MetroStore;
import nara.share.exception.store.AlreadyExistsException;
import nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class MetroJpaStore implements MetroStore {
    //
    @Autowired
    private MetroRepository metroRepository;

    @Autowired
    private MetroBookRepository metroBookRepository;

    private static final String METRO_BOOK_ID = "METRO";

    @Override
    public String create(Metro metro) {
        //
        String id = metro.getId();
        if (metroRepository.exists(id)) throw new AlreadyExistsException(String.format("Metro jpo[ID:%s] already exist.", id));
        MetroJpo metroJpo = MetroJpo.toJpo(metro);
        metroRepository.save(metroJpo);
        return id;
    }

    @Override
    public Metro retrieve(String id) throws NoSuchElementException {
        //
        MetroJpo metroJpo = metroRepository.findOne(id);
        if (metroJpo == null) throw new NoSuchElementException(String.format("No metro jpo[ID:%s] to retrieve.", id));
        return metroJpo.toDomain();
    }

    @Override
    public Metro retrieveByName(String metroName) {
        //
        MetroJpo metroJpo = metroRepository.findByName(metroName);
        if (metroJpo == null) return null;
        return metroJpo.toDomain();
    }

    @Override
    public Metro nextCitizenSequenceMetro(String id) throws NoSuchElementException {
        //
        MetroJpo metroJpo = metroRepository.findOne(id);
        if (metroJpo == null) throw new NoSuchElementException(String.format("No metro jpo[ID:%s] to retrieve.", id));
        metroJpo.increaseCitizenSequence(1);
        metroRepository.save(metroJpo);
        return metroJpo.toDomain();
    }

    @Override
    public boolean existByName(String name) {
        //
        MetroJpo metroJpo = metroRepository.findByName(name);
        if (metroJpo == null || metroJpo.getId() == null) return false;
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
        if (!metroBookRepository.exists(METRO_BOOK_ID)) {
            metroBookRepository.save(new MetroBookJpo(METRO_BOOK_ID));
        }
        return metroBookRepository.findOne(METRO_BOOK_ID).toDomain();
    }

    private void updateMetroBook(MetroBook metroBook) {
        //
        metroBookRepository.save(MetroBookJpo.toJpo(metroBook));
    }

    @Override
    public void update(Metro metro) {
        //
        String id = metro.getId();
        if (!metroRepository.exists(id)) throw new NonExistenceException(String.format("No metro jpo[ID:%s] to update.", id));
        MetroJpo metroJpo = MetroJpo.toJpo(metro);
        metroRepository.save(metroJpo);
    }

    @Override
    public List<Metro> retrieveAll() {
        //
        return MetroJpo.toDomains((List<MetroJpo>) metroRepository.findAll());
    }

    @Override
    public void delete(Metro metro) {
        //
        metroRepository.delete(metro.getId());
    }

}
