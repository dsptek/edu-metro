package nara.metro.adapter.rest;

import nara.metro.domain.entity.Citizen;
import nara.metro.domain.spec.CitizenProvider;
import nara.metro.domain.spec.sdo.CitizenCdo;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;

public class CitizenProviderRestAdapter implements CitizenProvider {
    //
    private NaraRestClient naraRestClient;

    public CitizenProviderRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String registerCitizen(CitizenCdo citizenCdo) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_P_REGISTER)
                        .setRequestBody(citizenCdo)
                        .setResponseType(String.class)
        );
    }

    @Override
    public String registerMetroAdminCitizen(CitizenCdo citizenCdo) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_P_ADMIN_REGISTER)
                        .setRequestBody(citizenCdo)
                        .setResponseType(String.class)
        );
    }

    @Override
    public Citizen findCitizen(String citizenId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_P_FIND)
                        .addPathParam("citizenId", citizenId)
                        .setResponseType(Citizen.class)
        );
    }

    @Override
    public List<Citizen> findCitizenByEmail(String email) {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_P_FIND_BY)
                        .addQueryParam("email", email)
                        .setResponseType(Citizen[].class)
        ));
    }

    @Override
    public Citizen findCitizenByMetroEmail(String metroId, String email) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_P_FIND_BY)
                        .addQueryParam("metroId", metroId)
                        .addQueryParam("email", email)
                        .setResponseType(Citizen.class)
        );
    }

    @Override
    public List<Citizen> findCitizenByMetro(String metroId, int offset, int limit) {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_P_FIND_BY)
                        .addQueryParam("metroId", metroId)
                        .addQueryParam("offset", offset)
                        .addQueryParam("limit", limit)
                        .setResponseType(Citizen[].class)
        ));
    }

    @Override
    public String disqualifyCitizen(String citizenId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_P_DISQUALIFY)
                .addPathParam("citizenId", citizenId)
                .setResponseType(String.class)
        );
    }

    @Override
    public boolean existsCitizenEmail(String metroId, String email) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.CITIZEN_P_EXISTS_BY)
                        .addQueryParam("metroId", metroId)
                        .addQueryParam("email", email)
                        .setResponseType(boolean.class)
        );
    }
}
