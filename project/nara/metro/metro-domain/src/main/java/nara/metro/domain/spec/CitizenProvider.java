package nara.metro.domain.spec;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.spec.sdo.CitizenCdo;

import java.util.List;

public interface CitizenProvider {
    //
    String registerCitizen(CitizenCdo citizenCdo);
    String registerMetroAdminCitizen(CitizenCdo citizenCdo);
    Citizen findCitizen(String citizenId);
    List<Citizen> findCitizenByEmail(String email);
    Citizen findCitizenByMetroEmail(String metroId, String email);
    List<Citizen> findCitizenByMetro(String metroId, int offset, int limit);
    String disqualifyCitizen(String citizenId);

    boolean existsCitizenEmail(String metroId, String email);
}