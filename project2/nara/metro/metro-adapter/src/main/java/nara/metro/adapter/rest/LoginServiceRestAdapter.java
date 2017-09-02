package nara.metro.adapter.rest;

import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.spec.LoginService;
import nara.metro.domain.spec.sdo.LoginUserCdo;
import nara.share.domain.NameValueList;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;

public class LoginServiceRestAdapter implements LoginService {
    //
    private NaraRestClient naraRestClient;

    public LoginServiceRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String registerLoginUser(String citizenId, LoginUserCdo loginUserCdo) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_S_REGISTER)
                .addPathParam("citizenId", citizenId)
                .setRequestBody(loginUserCdo)
                .setResponseType(String.class)
        );
    }

    @Override
    public LoginUser findLoginUser(String citizenId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_S_FIND)
                .addPathParam("citizenId", citizenId)
                .setResponseType(LoginUser.class)
        );
    }

    @Override
    public List<LoginUser> findLoginUserByMetro(String metroId, int offset, int limit) {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_S_FIND_BY)
                .addQueryParam("metroId", metroId)
                .addQueryParam("offset", offset)
                .addQueryParam("limit", limit)
                .setResponseType(LoginUser[].class)
        ));
    }

    @Override
    public void modifyLoginUser(String citizenId, NameValueList nameValues) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_S_MODIFY)
                    .addPathParam("citizenId", citizenId)
                    .setRequestBody(nameValues)
        );
    }

    @Override
    public void removeLoginUser(String citizenId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_S_REMOVE)
                .addPathParam("citizenId", citizenId)
        );
    }

    @Override
    public List<LoginTime> findLoginTimeByCitizen(String citizenId, int offset, int limit) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_S_LOGINTIME_FIND)
                    .addPathParam("citizenId", citizenId)
                    .addQueryParam("offset", offset)
                    .addQueryParam("limit", limit)
        );
    }

    @Override
    public void removeLoginTimesByCitizen(String citizenId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_S_LOGINTIME_REMOVE)
                .addPathParam("citizenId", citizenId)
        );
    }
}
