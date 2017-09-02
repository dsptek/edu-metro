package nara.metro.adapter.rest;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.entity.DisqualifiedCitizen;
import nara.metro.domain.spec.CitizenService;
import nara.metro.domain.spec.sdo.CitizenCdo;
import nara.share.domain.NameValueList;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;

public class CitizenServiceRestAdapter implements CitizenService {
    //
    private NaraRestClient naraRestClient;

    public CitizenServiceRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String registerCitizen(CitizenCdo citizenCdo) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_REGISTER)
                        .setRequestBody(citizenCdo)
                        .setResponseType(String.class)
        );
    }

    @Override
    public String registerMetroAdminCitizen(CitizenCdo citizenCdo) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_ADMIN_REGISTER)
                        .setRequestBody(citizenCdo)
                        .setResponseType(String.class)
        );
    }

    @Override
    public Citizen findCitizen(String citizenId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_FIND)
                        .addPathParam("citizenId", citizenId)
                        .setResponseType(Citizen.class)
        );
    }

    @Override
    public List<Citizen> findCitizenByEmail(String email) {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_FIND_BY)
                        .addQueryParam("email", email)
                        .setResponseType(Citizen[].class)
        ));
    }

    @Override
    public Citizen findCitizenByMetroEmail(String metroId, String email) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_FIND_BY)
                        .addQueryParam("metroId", metroId)
                        .addQueryParam("email", email)
                        .setResponseType(Citizen.class)
        );
    }

    @Override
    public List<Citizen> findCitizenByMetro(String metroId, int offset, int limit) {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_FIND_BY)
                        .addQueryParam("metroId", metroId)
                        .addQueryParam("offset", offset)
                        .addQueryParam("limit", limit)
                        .setResponseType(Citizen[].class)
        ));
    }

    @Override
    public void modifyCitizen(String citizenId, NameValueList nameValues) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_MODIFY)
                        .addPathParam("citizenId", citizenId)
                        .setRequestBody(nameValues)
        );
    }

    @Override
    public String disqualifyCitizen(String citizenId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_DISQUALIFY)
                .addPathParam("citizenId", citizenId)
                .setResponseType(String.class)
        );
    }

    @Override
    public List<DisqualifiedCitizen> findDisqualifiedCitizenByMetro(String metroId) {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_DISQUALIFIED_FIND)
                .addQueryParam("metroId", metroId)
                .setResponseType(DisqualifiedCitizen[].class)
        ));
    }

    @Override
    public boolean existsCitizenEmail(String metroId, String email) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_EXISTS_BY)
                        .addQueryParam("metroId", metroId)
                        .addQueryParam("email", email)
                        .setResponseType(boolean.class)
        );
    }

    @Override
    public boolean existsCitizenUsername(String metroId, String username) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_S_EXISTS_BY)
                        .addQueryParam("metroId", metroId)
                        .addQueryParam("username", username)
                        .setResponseType(boolean.class)
        );
    }
}
