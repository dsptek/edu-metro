package nara.metro.domain.store;

import nara.metro.domain.entity.Metro;
import nara.metro.domain.entity.MetroBook;

import java.util.List;
import java.util.NoSuchElementException;

public interface MetroStore {
    //
    String create(Metro metro);
    Metro retrieve(String id) throws NoSuchElementException;
    Metro retrieveByName(String metroName);
    Metro nextCitizenSequenceMetro(String id) throws NoSuchElementException;
    List<Metro> retrieveAll();
    void update(Metro metro);
    void delete(Metro metro);
    boolean existByName(String name);

    MetroBook nextSequenceBook();
    MetroBook nextVolumeSequenceBook(int volume);


}