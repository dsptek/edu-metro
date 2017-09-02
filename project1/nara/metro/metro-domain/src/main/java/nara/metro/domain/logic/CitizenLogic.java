package nara.metro.domain.logic;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.entity.Metro;
import nara.metro.domain.spec.CitizenProvider;
import nara.metro.domain.spec.CitizenService;
import nara.metro.domain.spec.sdo.CitizenCdo;
import nara.metro.domain.store.CitizenStore;
import nara.metro.domain.store.LoginStore;
import nara.metro.domain.store.MetroStore;
import nara.metro.domain.store.MetroStoreLycler;
import nara.share.domain.NameValueList;
import nara.share.domain.granule.Admin;
import nara.share.domain.granule.Name;
import nara.share.exception.store.AlreadyExistsException;

import java.util.List;
import java.util.NoSuchElementException;

public class CitizenLogic implements CitizenService, CitizenProvider {
    //
    private MetroStore metroStore;
    private CitizenStore citizenStore;
    private LoginStore loginStore;

    public CitizenLogic(MetroStoreLycler storeLycler) {
        //
        this.metroStore = storeLycler.requestMetroStore();
        this.citizenStore = storeLycler.requestCitizenStore();
        this.loginStore = storeLycler.requestLoginStore();
    }

    @Override
    public String registerCitizen(CitizenCdo citizenCdo) {
        //
        String metroId = citizenCdo.getMetroId();
        Name name = citizenCdo.getName();
        String email = citizenCdo.getEmail();

        Citizen citizen = citizenStore.retrieveByMetroEmail(metroId, email);
        if (citizen != null) {
            throw new AlreadyExistsException(String.format("metro:%s, email:%s", metroId, email));
        }

        Metro metro = metroStore.nextCitizenSequenceMetro(metroId);

        citizen = new Citizen(metroId, metro.getCitizenSequence(), name, email);
        citizen.setUsername(citizenCdo.getUsername());
        citizen.setPhone(citizenCdo.getPhone());

        citizenStore.create(citizen);

        LoginUser loginUser = new LoginUser(citizen, citizenCdo.getPassword());
        loginUser.setUsername(citizenCdo.getUsername());

        loginStore.create(loginUser);

        return citizen.getId();
    }

    @Override
    public String registerMetroAdminCitizen(CitizenCdo citizenCdo) {
        //
        String citizenId = registerCitizen(citizenCdo);

        Metro metro = metroStore.retrieve(citizenCdo.getMetroId());
        metro.getAdmins().add(new Admin(citizenId, citizenCdo.getName().getDisplayName()));
        metroStore.update(metro);

        return citizenId;
    }

    @Override
    public Citizen findCitizen(String citizenId) {
        //
        return citizenStore.retrieve(citizenId);
    }

    @Override
    public List<Citizen> findCitizenByEmail(String email) {
        //
        return citizenStore.retrieveByEmail(email);
    }

    @Override
    public Citizen findCitizenByMetroEmail(String metroId, String email) {
        //
        Citizen citizen = citizenStore.retrieveByMetroEmail(metroId, email);
        if (citizen == null) {
            throw new NoSuchElementException(String.format("metro:%s, email:%s", metroId, email));
        }

        return citizen;
    }

    @Override
    public List<Citizen> findCitizenByMetro(String metroId, int offset, int limit) {
        //
        return citizenStore.retrieveByMetro(metroId, offset, limit);
    }

    @Override
    public void modifyCitizen(String citizenId, NameValueList nameValues) {
        //
        Citizen citizen = findCitizen(citizenId);
        citizen.setValues(nameValues);

        citizenStore.update(citizen);
    }

    @Override
    public String disqualifyCitizen(String citizenId) {
        //
        Citizen citizen = findCitizen(citizenId);

        DisqualifiedCitizen disqualifiedCitizen = new DisqualifiedCitizen(citizen);
        citizenStore.createDisqualified(disqualifiedCitizen);
        citizenStore.delete(citizen);

        return disqualifiedCitizen.getId();
    }

    @Override
    public List<DisqualifiedCitizen> findDisqualifiedCitizenByMetro(String metroId) {
        //
        return citizenStore.retrieveDisqualifiedByMetro(metroId);
    }

    @Override
    public boolean existsCitizenEmail(String metroId, String email) {
        //
        return citizenStore.retrieveByMetroEmail(metroId, email) != null;
    }

    @Override
    public boolean existsCitizenUsername(String metroId, String username) {
        //
        return citizenStore.retrieveByMetroUsername(metroId, username) != null;
    }
}