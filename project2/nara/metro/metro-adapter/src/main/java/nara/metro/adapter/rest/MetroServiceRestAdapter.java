package nara.metro.adapter.rest;


import nara.metro.domain.entity.Metro;
import nara.metro.domain.spec.MetroService;
import nara.metro.domain.spec.sdo.MetroCdo;
import nara.share.domain.NameValueList;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;

public class MetroServiceRestAdapter implements MetroService {
    //
    private NaraRestClient naraRestClient;

    public MetroServiceRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String buildMetro(MetroCdo metroCdo) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_S_BUILD)
                .setRequestBody(metroCdo)
                .setResponseType(String.class)
        );
    }

    @Override
    public Metro findMetro(String metroId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_S_FIND)
                .addPathParam("metroId", metroId)
                .setResponseType(Metro.class)
        );
    }

    @Override
    public boolean existMetroByName(String name) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_S_EXIST_BY)
                .addQueryParam("name", name)
                .setResponseType(boolean.class)
        );
    }

    @Override
    public List<Metro> findMetros() {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_S_FINDALL)
                .setResponseType(Metro[].class)
        ));
    }

    @Override
    public void modifyMetro(String metroId, NameValueList nameValues) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_S_MODIFY)
                .addPathParam("metroId", metroId)
                .setRequestBody(nameValues)
        );
    }

    @Override
    public void removeMetro(String metroId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(MetroRestUrl.METRO_S_REMOVE)
                .addPathParam("metroId", metroId)
        );
    }

}