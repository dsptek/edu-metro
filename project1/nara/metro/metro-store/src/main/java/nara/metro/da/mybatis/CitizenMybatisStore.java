package nara.metro.da.mybatis;

import nara.metro.da.mybatis.mapper.CitizenMapper;
import nara.metro.da.mybatis.mapper.DisqualifiedCitizenMapper;
import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.metro.domain.store.CitizenStore;
import nara.share.exception.store.AlreadyExistsException;
import nara.share.exception.store.NonExistenceException;
import nara.share.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CitizenMybatisStore implements CitizenStore {
    //
    @Autowired
    private CitizenMapper citizenMapper;

    @Autowired
    private DisqualifiedCitizenMapper disqualifiedCitizenMapper;

    @Override
    public String create(Citizen citizen) {
        //
        String id = citizen.getId();
        if (citizenMapper.exists(id))
            throw new AlreadyExistsException(String.format("Citizen[%s] already exist.", id));

        String username = citizen.getUsername();
        String metroId = citizen.getMetroId();
        if (StringUtil.isNotEmpty(username) && citizenMapper.existsByMetroIdAndUsername(metroId, username))
            throw new AlreadyExistsException(String.format("Citizen[username:%s] already exists in metro[%s]", username, metroId));

        try {
            citizenMapper.insert(citizen);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(String.format("Citizen[%s] already exists in metro[%s]", citizen.getEmail(), metroId));
        }
        return id;
    }

    @Override
    public Citizen retrieve(String id) throws NoSuchElementException {
        //
        Citizen citizen = citizenMapper.findOne(id);
        if (citizen == null)
            throw new NoSuchElementException(String.format("No citizen[%s] to retrieve.", id));
        return citizen;
    }

    @Override
    public List<Citizen> retrieveByName(String metroId, String name) {
        //
        return citizenMapper.findByMetroIdAndDisplayName(metroId, name);
    }

    @Override
    public Citizen retrieveByMetroEmail(String metroId, String email) {
        //
        return citizenMapper.findByMetroIdAndEmail(metroId, email);
    }

    @Override
    public Citizen retrieveByMetroUsername(String metroId, String username) {
        //
        return citizenMapper.findByMetroIdAndUsername(metroId, username);
    }

    @Override
    public List<Citizen> retrieveByEmail(String email) {
        //
        return citizenMapper.findByEmail(email);
    }

    @Override
    public List<Citizen> retrieveByMetro(String metroId, int offset, int limit) {
        //
        return citizenMapper.findByMetroId(metroId, offset, limit);
    }

    @Override
    public void update(Citizen citizen) {
        //
        if (!citizenMapper.exists(citizen.getId()))
            throw new NonExistenceException(String.format("No citizen[%s] to update.", citizen.getId()));
        citizenMapper.update(citizen);
    }

    @Override
    public void delete(Citizen citizen) {
        //
        citizenMapper.delete(citizen.getId());
    }

    @Override
    public void createDisqualified(DisqualifiedCitizen citizen) {
        //
        String id = citizen.getId();
        if (disqualifiedCitizenMapper.exists(id))
            throw new AlreadyExistsException(String.format("DisqualifiedCitizen[%s] is already exist.", id));
        disqualifiedCitizenMapper.insert(citizen);
    }

    @Override
    public DisqualifiedCitizen retrieveDisqualified(String id) throws NoSuchElementException {
        //
        DisqualifiedCitizen disqualifiedCitizen = disqualifiedCitizenMapper.findOne(id);
        if (disqualifiedCitizen == null)
            throw new NoSuchElementException(String.format("No disqualified citizen[%s] to retrieve.", id));

        return disqualifiedCitizen;
    }

    @Override
    public List<DisqualifiedCitizen> retrieveDisqualifiedByMetro(String metroId) {
        //
        return disqualifiedCitizenMapper.findByMetroId(metroId);
    }

    @Override
    public void delete(DisqualifiedCitizen citizen) {
        //
        disqualifiedCitizenMapper.delete(citizen.getId());
    }
}
