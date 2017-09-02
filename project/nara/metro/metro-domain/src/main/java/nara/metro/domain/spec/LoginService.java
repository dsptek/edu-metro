package nara.metro.domain.spec;

import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.spec.sdo.LoginUserCdo;
import nara.share.domain.NameValueList;

import java.util.List;

public interface LoginService {
    //
    String registerLoginUser(String citizenId, LoginUserCdo loginUserCdo);
    LoginUser findLoginUser(String citizenId);
    List<LoginUser> findLoginUserByMetro(String metroId, int offset, int limit);
    void modifyLoginUser(String citizenId, NameValueList nameValues);
    void removeLoginUser(String citizenId);
    List<LoginTime> findLoginTimeByCitizen(String citizenId, int offset, int limit);
    void removeLoginTimesByCitizen(String citizenId);
}
