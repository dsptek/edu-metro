package nara.metro.domain.spec;

import nara.metro.domain.entity.LoginUser;

public interface LoginProvider {
    //
    LoginUser findLoginUserByUsername(String metroId, String username);
    LoginUser findLoginUserByEmail(String metroId, String email);
    void registerLoginTime(String citizenId);
}
