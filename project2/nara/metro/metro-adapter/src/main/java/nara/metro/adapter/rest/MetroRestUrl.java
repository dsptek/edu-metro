package nara.metro.adapter.rest;

import nara.share.restclient.HttpMethod;
import nara.share.restclient.NaraRestUrl;

public enum MetroRestUrl implements NaraRestUrl {
    //
    METRO_S_BUILD                   ("api/s/metros",                                         HttpMethod.POST    ),
    METRO_S_FIND                    ("api/s/metros/{metroId}",                               HttpMethod.GET     ),
    METRO_S_EXIST_BY                ("api/s/metros/exists",                                  HttpMethod.GET     ),
    METRO_S_FINDALL                 ("api/s/metros",                                         HttpMethod.GET     ),
    METRO_S_MODIFY                  ("api/s/metros/{metroId}",                               HttpMethod.PUT     ),
    METRO_S_REMOVE                  ("api/s/metros/{metroId}",                               HttpMethod.DELETE  ),

    METRO_P_BUILD                   ("api/p/metros",                                         HttpMethod.POST    ),
    METRO_P_FIND                    ("api/p/metros/{metroId}",                               HttpMethod.GET     ),
    METRO_P_FIND_BY                 ("api/p/metros",                                         HttpMethod.GET     ),
    METRO_P_EXIST_BY                ("api/p/metros/exists",                                  HttpMethod.GET     ),
    METRO_P_MODIFY                  ("api/p/metros/{metroId}",                               HttpMethod.PUT     ),
    METRO_P_NEXT_PAV_SEQ            ("api/p/metros/{metroId}/next-pavilion-sequence",        HttpMethod.GET     ),

    CITIZEN_S_REGISTER              ("api/s/citizens",                                       HttpMethod.POST    ),
    CITIZEN_S_ADMIN_REGISTER        ("api/s/citizens/admin",                                 HttpMethod.POST    ),
    CITIZEN_S_FIND                  ("api/s/citizens/{citizenId}",                           HttpMethod.GET     ),
    CITIZEN_S_FIND_BY               ("api/s/citizens",                                       HttpMethod.GET     ),
    CITIZEN_S_MODIFY                ("api/s/citizens/{citizenId}",                           HttpMethod.PUT     ),
    CITIZEN_S_DISQUALIFY            ("api/s/citizens/{citizenId}/disqualification",          HttpMethod.PUT     ),
    CITIZEN_S_DISQUALIFIED_FIND     ("api/s/disqualified-citizens",                          HttpMethod.GET     ),
    CITIZEN_S_EXISTS_BY             ("api/s/citizens/exists",                                HttpMethod.GET     ),

    CITIZEN_P_REGISTER              ("api/p/citizens",                                       HttpMethod.POST    ),
    CITIZEN_P_ADMIN_REGISTER        ("api/p/citizens/admin",                                 HttpMethod.POST    ),
    CITIZEN_P_FIND                  ("api/p/citizens/{citizenId}",                           HttpMethod.GET     ),
    CITIZEN_P_FIND_BY               ("api/p/citizens",                                       HttpMethod.GET     ),
    CITIZEN_P_DISQUALIFY            ("api/p/citizens/{citizenId}/disqualification",          HttpMethod.PUT     ),
    CITIZEN_P_EXISTS_BY             ("api/p/citizens/exists",                                HttpMethod.GET     ),

    LOGIN_USER_S_REGISTER           ("api/s/login-users/{citizenId}",                        HttpMethod.POST    ),
    LOGIN_USER_S_FIND               ("api/s/login-users/{citizenId}",                        HttpMethod.GET     ),
    LOGIN_USER_S_FIND_BY            ("api/s/login-users",                                    HttpMethod.GET     ),
    LOGIN_USER_S_MODIFY             ("api/s/login-users/{citizenId}",                        HttpMethod.PUT     ),
    LOGIN_USER_S_REMOVE             ("api/s/login-users/{citizenId}",                        HttpMethod.DELETE  ),
    LOGIN_USER_S_LOGINTIME_FIND     ("api/s/login-users/{citizenId}/login-times",            HttpMethod.GET     ),
    LOGIN_USER_S_LOGINTIME_REMOVE   ("api/s/login-users/{citizenId}/login-times",            HttpMethod.DELETE  ),

    LOGIN_USER_P_FIND_BY            ("api/p/login-users",                                    HttpMethod.GET     ),
    LOGIN_USER_P_LOGINTIME_REGISTER ("api/p/login-users/{citizenId}/login-times",            HttpMethod.POST    ),

    ;

    private String restUrl;
    private HttpMethod method;

    MetroRestUrl(String restUrl, HttpMethod method) {
        //
        this.restUrl = restUrl;
        this.method = method;
    }


    @Override
    public String getUrl() {
        return this.restUrl;
    }

    @Override
    public HttpMethod getMethod() {
        return this.method;
    }
}
