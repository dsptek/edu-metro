package nara.metro.domain.store;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;

import java.util.List;
import java.util.NoSuchElementException;

public interface CitizenStore {
    //
    String create(Citizen citizen);
    Citizen retrieve(String id) throws NoSuchElementException;
    List<Citizen> retrieveByName(String metroId, String name);
    Citizen retrieveByMetroEmail(String metroId, String email);
    Citizen retrieveByMetroUsername(String metroId, String username);
    List<Citizen> retrieveByEmail(String email);
    List<Citizen> retrieveByMetro(String metroId, int offset, int limit);
    void update(Citizen citizen);
    void delete(Citizen citizen);  // disqualified then delete

    void createDisqualified(DisqualifiedCitizen citizen);
    DisqualifiedCitizen retrieveDisqualified(String id) throws NoSuchElementException;
    List<DisqualifiedCitizen> retrieveDisqualifiedByMetro(String metroId);
    void delete(DisqualifiedCitizen citizen);
}