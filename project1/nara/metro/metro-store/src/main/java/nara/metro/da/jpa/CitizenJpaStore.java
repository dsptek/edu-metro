package nara.metro.da.jpa;

import nara.metro.da.jpa.jpo.CitizenJpo;
import nara.metro.da.jpa.jpo.DisqualifiedCitizenJpo;
import nara.metro.da.jpa.springdata.CitizenRepository;
import nara.metro.da.jpa.springdata.DisqualifiedCitizenRepository;
import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.metro.domain.store.CitizenStore;
import nara.share.exception.store.AlreadyExistsException;
import nara.share.exception.store.NonExistenceException;
import nara.share.springdata.page.OffsetRequest;
import nara.share.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CitizenJpaStore implements CitizenStore {
    //
    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private DisqualifiedCitizenRepository disqualifiedCitizenRepository;

    @Override
    public String create(Citizen citizen) {
        //
        String id = citizen.getId();
        if (citizenRepository.exists(id))
            throw new AlreadyExistsException(String.format("Citizen jpo[ID:%s] already exist.", id));

        String username = citizen.getUsername();
        String metroId = citizen.getMetroId();
        if (StringUtil.isNotEmpty(username) && citizenRepository.countByMetroIdAndUsername(metroId, username) > 0)
            throw new AlreadyExistsException(String.format("Citizen[username:%s] already exists in metro[%s].", username, metroId));

        CitizenJpo citizenJpo = CitizenJpo.toJpo(citizen);
        try {
            citizenRepository.save(citizenJpo);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(String.format("Citizen[%s] already exists in metro[%s].", citizen.getEmail(), metroId), e);
        }
        return id;
    }

    @Override
    public Citizen retrieve(String id) throws NoSuchElementException {
        //
        CitizenJpo citizenJpo = citizenRepository.findOne(id);
        if (citizenJpo == null)
            throw new NoSuchElementException(String.format("No citizen jpo[ID:%s] to retrieve.", id));
        return citizenJpo.toDomain();
    }

    @Override
    public List<Citizen> retrieveByName(String metroId, String name) {
        //
        List<CitizenJpo> citizenJpos = citizenRepository.findByMetroIdAndDisplayName(metroId, name);
        return CitizenJpo.toDomains(citizenJpos);
    }

    @Override
    public Citizen retrieveByMetroEmail(String metroId, String email) {
        //
        CitizenJpo citizenJpo = citizenRepository.findByMetroIdAndEmail(metroId, email);
        if (citizenJpo == null) return null;
        return citizenJpo.toDomain();
    }

    @Override
    public Citizen retrieveByMetroUsername(String metroId, String username) {
        //
        CitizenJpo citizenJpo = citizenRepository.findByMetroIdAndUsername(metroId, username);
        if (citizenJpo == null) return null;
        return citizenJpo.toDomain();
    }

    @Override
    public List<Citizen> retrieveByEmail(String email) {
        //
        List<CitizenJpo> citizenJpos = citizenRepository.findByEmail(email);
        return CitizenJpo.toDomains(citizenJpos);
    }

    @Override
    public List<Citizen> retrieveByMetro(String metroId, int offset, int limit) {
        //
        List<CitizenJpo> citizenJpos = citizenRepository.findByMetroIdOrderBySequence(metroId, new OffsetRequest(offset, limit));
        return CitizenJpo.toDomains(citizenJpos);
    }

    @Override
    public void update(Citizen citizen) {
        //
        if (!citizenRepository.exists(citizen.getId()))
            throw new NonExistenceException(String.format("No citizen document[ID:%s] to update.", citizen.getId()));
        CitizenJpo citizenJpo = CitizenJpo.toJpo(citizen);
        citizenRepository.save(citizenJpo);
    }

    @Override
    public void delete(Citizen citizen) {
        //
        citizenRepository.delete(citizen.getId());
    }

    @Override
    public void createDisqualified(DisqualifiedCitizen citizen) {
        //
        String id = citizen.getId();
        if (disqualifiedCitizenRepository.exists(id))
            throw new AlreadyExistsException(String.format("DisqualifiedCitizen jpo[ID:%s] already exist.", id));

        DisqualifiedCitizenJpo disqualifiedCitizenJpo = DisqualifiedCitizenJpo.toJpo(citizen);
        disqualifiedCitizenRepository.save(disqualifiedCitizenJpo);
    }

    @Override
    public DisqualifiedCitizen retrieveDisqualified(String id) throws NoSuchElementException {
        //
        DisqualifiedCitizenJpo jpo = disqualifiedCitizenRepository.findOne(id);
        if (jpo == null)
            throw new NoSuchElementException(String.format("No disqualified citizen jpo[ID:%s] to retrieve.", id));
        return jpo.toDomain();
    }

    @Override
    public List<DisqualifiedCitizen> retrieveDisqualifiedByMetro(String metroId) {
        //
        List<DisqualifiedCitizenJpo> citizenJpos = disqualifiedCitizenRepository.findByMetroId(metroId);
        return DisqualifiedCitizenJpo.toDomains(citizenJpos);
    }

    @Override
    public void delete(DisqualifiedCitizen citizen) {
        //
        disqualifiedCitizenRepository.delete(citizen.getId());
    }
}
