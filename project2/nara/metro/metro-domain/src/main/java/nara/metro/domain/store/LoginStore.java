package nara.metro.domain.store;

import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;

import java.util.List;
import java.util.NoSuchElementException;

public interface LoginStore {
    //
    void create(LoginUser loginUser);
    LoginUser retrieve(String id) throws NoSuchElementException;
    LoginUser retrieveByMetroIdAndEmail(String metroId, String email);
    LoginUser retrieveByMetroIdAndUserName(String metroId, String userName);
    List<LoginUser> retrieveByMetroId(String metroId, int offset, int limit);
    void update(LoginUser loginUser);
    void delete(LoginUser loginUser);

    void createLoginTime(LoginTime loginTime);
    List<LoginTime> retrieveLoginTimeByCitizenId(String citizenId, int offset, int limit);
    void deleteLoginTime(String id);
    void deleteLoginTimesByCitizenId(String citizenId);
}