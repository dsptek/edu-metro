package nara.share.restclient.jaxrs;

import nara.share.exception.rest.NaraRestClientException;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JaxRSClient implements NaraRestClient {
    //
    private Logger logger = LoggerFactory.getLogger(getClass());

    private WebTarget webTarget;
    private Client client;

    private static final String JSESSIONID = "SESSION";
    private String jSessionId = null;

    public JaxRSClient(String host) {
        //
        this.client = ClientBuilder.newClient();
        this.client.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);
        this.webTarget = client.target(host);
    }

    @Override
    public <T> T sendAndRecieve(RequestBuilder requestBuilder) {
        //
        WebTarget resource = webTarget;

        if (jSessionId != null) {
            requestBuilder.addHeader("Cookie", JSESSIONID + "=" + jSessionId);
        }

        Object request = requestBuilder.getRequestBody();
        Class<T> clazz = requestBuilder.getResponseType();
        MultivaluedMap<String, Object> headers = toMultivaluedMap(requestBuilder.getHeaders());


        Map<String, String> pathParams = requestBuilder.getPathParams();
        MultiValueMap<String, String> queryParams = requestBuilder.getQueryParams();

        String url = requestBuilder.getUrl();
        String httpMethod = requestBuilder.getMethod();

        if (pathParams != null) {
            Set<String> keys = pathParams.keySet();
            for(String key : keys) {
                url = url.replace("{" + key + "}", pathParams.get(key));
            }
        }

        if (queryParams != null) {
            for(String key : queryParams.keySet()) {
                List<String> values = queryParams.get(key);
                resource = resource.queryParam(key, values.toArray(new String[values.size()]));
            }
        }

        Invocation invocation = resource
                .path(url)
                .request(MediaType.APPLICATION_JSON)
                .headers(headers)
                .build(httpMethod, Entity.entity(request, MediaType.APPLICATION_JSON));

        Response response = invocation.invoke();
        MultivaluedMap<String, Object> responseHeaders = response.getHeaders();
        List<Object> cookies = responseHeaders.get("Set-Cookie");

        if (cookies != null) {
            for (Object cookie: cookies) {
                String[] cookieEntries = cookie.toString().split(";");
                for (String cookieEntry : cookieEntries) {
                    cookieEntry = cookieEntry.trim();
                    if (cookieEntry.startsWith(JSESSIONID)) {
                        String[] split = cookieEntry.split("=");
                        if (split.length <= 1) {
                            jSessionId = null;
                        }
                        else {
                            jSessionId = split[1];
                        }
                        break;
                    }
                }
            }
        }

        if (Response.Status.Family.SERVER_ERROR.equals(Response.Status.Family.familyOf(response.getStatus()))) {
            String exceptionMessage = response.readEntity(String.class);
            throw new NaraRestClientException(exceptionMessage);
        }

        if (response.hasEntity()) {
            return response.readEntity(clazz);
        }
        else {
            return null;
        }
    }

    private MultivaluedMap<String, Object> toMultivaluedMap(HttpHeaders headers) {
        //
        if (headers == null) return null;
        MultivaluedMap<String, Object> mvm = new MultivaluedHashMap<>();
        for (String key : headers.keySet()) {
            mvm.put(key, new ArrayList<>(headers.get(key)));
        }
        return mvm;
    }
}
