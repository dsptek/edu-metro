package nara.metro.domain.logic;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.spec.LoginProvider;
import nara.metro.domain.spec.LoginService;
import nara.metro.domain.spec.sdo.LoginUserCdo;
import nara.metro.domain.store.CitizenStore;
import nara.metro.domain.store.LoginStore;
import nara.metro.domain.store.MetroStoreLycler;
import nara.share.domain.NameValueList;

import java.time.ZonedDateTime;
import java.util.List;

public class LoginLogic implements LoginService, LoginProvider {
    //
    private CitizenStore citizenStore;
    private LoginStore loginStore;

    public LoginLogic(MetroStoreLycler storeLycler) {
        //
        this.citizenStore = storeLycler.requestCitizenStore();
        this.loginStore = storeLycler.requestLoginStore();
    }

    @Override
    public String registerLoginUser(String citizenId, LoginUserCdo loginUserCdo) {
        //
        Citizen citizen = citizenStore.retrieve(citizenId);

        LoginUser loginUser = new LoginUser(citizen, loginUserCdo.getPassword());
        loginUser.setUsername(loginUserCdo.getUsername());

        loginStore.create(loginUser);

        return loginUser.getId();
    }

    @Override
    public LoginUser findLoginUser(String citizenId) {
        //
        return loginStore.retrieve(citizenId);
    }

    @Override
    public List<LoginUser> findLoginUserByMetro(String metroId, int offset, int limit) {
        //
        return loginStore.retrieveByMetroId(metroId, offset, limit);
    }

    @Override
    public LoginUser findLoginUserByUsername(String metroId, String username) {
        //
        return loginStore.retrieveByMetroIdAndUserName(metroId, username);
    }

    @Override
    public LoginUser findLoginUserByEmail(String metroId, String email) {
        //
        return loginStore.retrieveByMetroIdAndEmail(metroId, email);
    }

    @Override
    public void registerLoginTime(String citizenId) {
        //
        loginStore.createLoginTime(new LoginTime(citizenId, ZonedDateTime.now()));
    }

    @Override
    public void modifyLoginUser(String citizenId, NameValueList nameValues) {
        //
        LoginUser loginUser = loginStore.retrieve(citizenId);
        loginUser.setValues(nameValues);
        loginStore.update(loginUser);
    }

    @Override
    public void removeLoginUser(String citizenId) {
        //
        LoginUser loginUser = loginStore.retrieve(citizenId);
        loginStore.delete(loginUser);
    }

    @Override
    public List<LoginTime> findLoginTimeByCitizen(String citizenId, int offset, int limit) {
        //
        return loginStore.retrieveLoginTimeByCitizenId(citizenId, offset, limit);
    }

    @Override
    public void removeLoginTimesByCitizen(String citizenId) {
        //
        loginStore.deleteLoginTimesByCitizenId(citizenId);
    }

}