package nara.metro.adapter.rest;

import nara.metro.domain.entity.Metro;
import nara.metro.domain.spec.MetroProvider;
import nara.metro.domain.spec.sdo.MetroCdo;
import nara.share.domain.NameValueList;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;

public class MetroProviderRestAdapter implements MetroProvider {
    //
    private NaraRestClient naraRestClient;

    public MetroProviderRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String buildMetro(MetroCdo metroCdo) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_P_BUILD)
                        .setRequestBody(metroCdo)
                        .setResponseType(String.class)
        );
    }

    @Override
    public Metro findMetro(String metroId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_P_FIND)
                        .addPathParam("metroId", metroId)
                        .setResponseType(Metro.class)
        );
    }

    @Override
    public Metro findMetroByName(String name) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_P_FIND_BY)
                        .addQueryParam("name", name)
                        .setResponseType(Metro.class)
        );
    }

    @Override
    public List<Metro> findMetros() {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_P_FIND_BY)
                        .setResponseType(Metro[].class)
        ));
    }

    @Override
    public boolean existMetroByName(String name) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_P_EXIST_BY)
                        .addQueryParam("name", name)
                        .setResponseType(boolean.class)
        );
    }

    @Override
    public void modifyMetro(String metroId, NameValueList nameValues) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_P_MODIFY)
                        .addPathParam("metroId", metroId)
                        .setRequestBody(nameValues)
        );
    }

    @Override
    public long nextPavilionSequence(String metroId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_P_NEXT_PAV_SEQ)
                        .addPathParam("metroId", metroId)
                        .setResponseType(long.class)
        );
    }
}
