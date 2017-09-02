package nara.metro.da.mybatis;

import nara.metro.da.mybatis.mapper.LoginTimeMapper;
import nara.metro.da.mybatis.mapper.LoginUserMapper;
import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.store.LoginStore;
import nara.share.exception.store.AlreadyExistsException;
import nara.share.exception.store.NonExistenceException;
import nara.share.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class LoginMybatisStore implements LoginStore {
    //
    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private LoginTimeMapper loginTimeMapper;

    @Override
    public void create(LoginUser loginUser) {
        //
        String id = loginUser.getId();
        if (loginUserMapper.exists(id))
            throw new AlreadyExistsException(String.format("Login user[%s] already exist.", id));

        String username = loginUser.getUsername();
        String metroId = loginUser.getMetroId();
        if (StringUtil.isNotEmpty(username) && loginUserMapper.existsByMetroIdAndUsername(metroId, username))
            throw new AlreadyExistsException(String.format("Login user[%s] already exists in metro[%s]", username, metroId));

        try {
            loginUserMapper.insert(loginUser);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(String.format("Login user[%s] already exists in metro[%s]", loginUser.getEmail(), metroId));
        }
    }

    @Override
    public LoginUser retrieve(String id) throws NoSuchElementException {
        //
        LoginUser loginUser = loginUserMapper.findOne(id);
        if (loginUser == null)
            throw new NoSuchElementException(String.format("No login user[%s] to retrieve.", id));

        return loginUser;
    }

    @Override
    public LoginUser retrieveByMetroIdAndEmail(String metroId, String email) {
        //
        return loginUserMapper.findByMetroIdAndEmail(metroId, email);
    }

    @Override
    public LoginUser retrieveByMetroIdAndUserName(String metroId, String username) {
        //
        return loginUserMapper.findByMetroIdAndUsername(metroId, username);
    }

    @Override
    public List<LoginUser> retrieveByMetroId(String metroId, int offset, int limit) {
        //
        return loginUserMapper.findByMetroId(metroId, offset, limit);
    }

    @Override
    public void update(LoginUser loginUser) {
        //
        if (!loginUserMapper.exists(loginUser.getId()))
            throw new NonExistenceException(String.format("No login user[%s] to update.", loginUser.getId()));
        loginUserMapper.update(loginUser);
    }

    @Override
    public void delete(LoginUser loginUser) {
        //
        loginUserMapper.delete(loginUser.getId());
    }

    @Override
    public void createLoginTime(LoginTime loginTime) {
        //
        String id = loginTime.getId();
        if (loginTimeMapper.exists(id))
            throw new AlreadyExistsException(String.format("Login time[%s] already exists.", id));
        
        loginTimeMapper.insert(loginTime);
    }

    @Override
    public List<LoginTime> retrieveLoginTimeByCitizenId(String citizenId, int offset, int limit) {
        //
        return loginTimeMapper.findByCitizenId(citizenId, offset, limit);
    }

    @Override
    public void deleteLoginTime(String id) {
        //
        loginTimeMapper.delete(id);
    }

    @Override
    public void deleteLoginTimesByCitizenId(String citizenId) {
        //
        loginTimeMapper.deleteByCitizenId(citizenId);
    }
}
