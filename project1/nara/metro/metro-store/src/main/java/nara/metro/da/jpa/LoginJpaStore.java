package nara.metro.da.jpa;

import nara.metro.da.jpa.jpo.LoginTimeJpo;
import nara.metro.da.jpa.jpo.LoginUserJpo;
import nara.metro.da.jpa.springdata.LoginTimeRepository;
import nara.metro.da.jpa.springdata.LoginUserRepository;
import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.store.LoginStore;
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
public class LoginJpaStore implements LoginStore {
    //
    @Autowired
    private LoginUserRepository loginUserRepository;

    @Autowired
    private LoginTimeRepository loginTimeRepository;

    @Override
    public void create(LoginUser loginUser) {
        //
        String id = loginUser.getId();
        if (loginUserRepository.exists(id))
            throw new AlreadyExistsException(String.format("Login user jpo[ID:%s] already exist.", id));

        String username = loginUser.getUsername();
        String metroId = loginUser.getMetroId();
        if (StringUtil.isNotEmpty(username) && loginUserRepository.countByMetroIdAndUsername(metroId, username) > 0)
            throw new AlreadyExistsException(String.format("Login user[username:%s] already exists in metro[%s]", username, metroId));

        LoginUserJpo loginUserJpo = LoginUserJpo.toJpo(loginUser);
        try {
            loginUserRepository.save(loginUserJpo);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(String.format("Login user[%s] already exists in metro[%s].", loginUser.getEmail(), metroId), e);
        }
    }

    @Override
    public LoginUser retrieve(String id) throws NoSuchElementException {
        //
        LoginUserJpo loginUserJpo = loginUserRepository.findOne(id);
        if (loginUserJpo == null)
            throw new NoSuchElementException(String.format("No login user jpo[ID:%s] to retrieve.", id));
        return loginUserJpo.toDomain();
    }

    @Override
    public LoginUser retrieveByMetroIdAndEmail(String metroId, String email) {
        //
        LoginUserJpo loginUserJpo = loginUserRepository.findByMetroIdAndEmail(metroId, email);
        if (loginUserJpo == null) return null;
        return loginUserJpo.toDomain();
    }

    @Override
    public LoginUser retrieveByMetroIdAndUserName(String metroId, String username) {
        //
        LoginUserJpo loginUserJpo = loginUserRepository.findByMetroIdAndUsername(metroId, username);
        if (loginUserJpo == null) return null;
        return loginUserJpo.toDomain();
    }

    @Override
    public List<LoginUser> retrieveByMetroId(String metroId, int offset, int limit) {
        //
        List<LoginUserJpo> loginUserJpos = loginUserRepository.findByMetroIdOrderByEmail(metroId, new OffsetRequest(offset, limit));
        return LoginUserJpo.toDomains(loginUserJpos);
    }

    @Override
    public void update(LoginUser loginUser) {
        //
        if (!loginUserRepository.exists(loginUser.getId()))
            throw new NonExistenceException(String.format("No login user jpo[ID:%s] to update", loginUser.getId()));
        LoginUserJpo loginUserJpo = LoginUserJpo.toJpo(loginUser);
        loginUserRepository.save(loginUserJpo);
    }

    @Override
    public void delete(LoginUser loginUser) {
        //
        loginUserRepository.delete(loginUser.getId());
    }

    @Override
    public void createLoginTime(LoginTime loginTime) {
        //
        String id = loginTime.getId();
        if (loginTimeRepository.exists(id))
            throw new AlreadyExistsException(String.format("Login time jpo[ID:%s] already exist.", id));
        LoginTimeJpo loginTimeJpo = LoginTimeJpo.toJpo(loginTime);
        loginTimeRepository.save(loginTimeJpo);
    }

    @Override
    public List<LoginTime> retrieveLoginTimeByCitizenId(String citizenId, int offset, int limit) {
        //
        List<LoginTimeJpo> loginTimeJpos = loginTimeRepository.findByCitizenId(citizenId, new OffsetRequest(offset, limit));
        return LoginTimeJpo.toDomains(loginTimeJpos);
    }

    @Override
    public void deleteLoginTime(String id) {
        //
        loginTimeRepository.delete(id);
    }

    @Override
    public void deleteLoginTimesByCitizenId(String citizenId) {
        //
        loginTimeRepository.deleteByCitizenId(citizenId);
    }
}
