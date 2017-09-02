package nara.metro.da.mybatis;

import nara.metro.da.mybatis.mapper.MetroBookMapper;
import nara.metro.da.mybatis.mapper.MetroMapper;
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
public class MetroMybatisStore implements MetroStore {
    //
    @Autowired
    private MetroMapper metroMapper;

    @Autowired
    private MetroBookMapper metroBookMapper;

    private static final String METRO_BOOK_ID = "METRO";

    @Override
    public String create(Metro metro) {
        //
        String id = metro.getId();
        if (metroMapper.exists(id)) throw new AlreadyExistsException(String.format("Metro[%s] already exist.", id));
        metroMapper.insert(metro);
        return id;
    }

    @Override
    public Metro retrieve(String id) throws NoSuchElementException {
        //
        Metro metro = metroMapper.findOne(id);
        if (metro == null) throw new NoSuchElementException(String.format("No metro[%s] to retrieve.", id));
        return metro;
    }

    @Override
    public Metro retrieveByName(String metroName) {
        // TODO
        return null;
    }

    @Override
    public Metro nextCitizenSequenceMetro(String id) throws NoSuchElementException {
        //
        Metro metro = metroMapper.findOne(id);
        if (metro == null) throw new NoSuchElementException(String.format("No metro[%s] to retrieve.", id));
        metro.nextCitizenSequence();
        metroMapper.update(metro);
        return metro;
    }

    @Override
    public List<Metro> retrieveAll() {
        return metroMapper.findAll();
    }

    @Override
    public void update(Metro metro) {
        //
        String id = metro.getId();
        if (!metroMapper.exists(id)) throw new NonExistenceException(String.format("No metro[%s] to update.", id));
        metroMapper.update(metro);
    }

    @Override
    public void delete(Metro metro) {
        metroMapper.delete(metro.getId());
    }

    @Override
    public boolean existByName(String name) {
        return metroMapper.existByName(name);
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
        MetroBook metroBook = getMetroBook();
        metroBook.increaseSequence(volume);
        updateMetroBook(metroBook);
        return metroBook;
    }

    private MetroBook getMetroBook() {
        //
        if (!metroBookMapper.exists(METRO_BOOK_ID)) {
            metroBookMapper.insert(new MetroBook(METRO_BOOK_ID));
        }
        return metroBookMapper.findOne(METRO_BOOK_ID);
    }

    private void updateMetroBook(MetroBook metroBook) {
        //
        metroBookMapper.update(metroBook);
    }
}
