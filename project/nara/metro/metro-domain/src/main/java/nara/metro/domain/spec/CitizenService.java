package nara.metro.domain.spec;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.metro.domain.spec.sdo.CitizenCdo;
import nara.share.domain.NameValueList;

import java.util.List;

public interface CitizenService {
    //
    String registerCitizen(CitizenCdo citizenCdo);
    String registerMetroAdminCitizen(CitizenCdo citizenCdo);
    Citizen findCitizen(String citizenId);
    List<Citizen> findCitizenByEmail(String email);
    Citizen findCitizenByMetroEmail(String metroId, String email);
    List<Citizen> findCitizenByMetro(String metroId, int offset, int limit);
    void modifyCitizen(String citizenId, NameValueList nameValues);
    String disqualifyCitizen(String citizenId);
    List<DisqualifiedCitizen> findDisqualifiedCitizenByMetro(String metroId);

    boolean existsCitizenEmail(String metroId, String email);
    boolean existsCitizenUsername(String metroId, String username);
}