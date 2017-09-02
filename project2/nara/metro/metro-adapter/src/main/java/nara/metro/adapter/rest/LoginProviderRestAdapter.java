package nara.metro.adapter.rest;

import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.spec.LoginProvider;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;

public class LoginProviderRestAdapter implements LoginProvider {
    //
    private NaraRestClient naraRestClient;

    public LoginProviderRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public LoginUser findLoginUserByUsername(String metroId, String username) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_P_FIND_BY)
                .addQueryParam("metroId", metroId)
                .addQueryParam("username", username)
                .setResponseType(LoginUser.class)
        );
    }

    @Override
    public LoginUser findLoginUserByEmail(String metroId, String email) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_P_FIND_BY)
                        .addQueryParam("metroId", metroId)
                        .addQueryParam("email", email)
                        .setResponseType(LoginUser.class)
        );
    }

    @Override
    public void registerLoginTime(String citizenId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.LOGIN_USER_P_LOGINTIME_REGISTER)
                .addPathParam("citizenId", citizenId)
        );
    }
}
