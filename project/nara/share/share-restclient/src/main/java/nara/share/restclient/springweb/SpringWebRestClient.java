package nara.share.restclient.springweb;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import nara.share.exception.rest.NaraRestClientException;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpringWebRestClient implements NaraRestClient {
    //
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate;
    private String serverHost;

    private static final String JSESSIONID = "SESSION";
    private String jSessionId = null;

    public SpringWebRestClient(String serverHost) {
        //
        if (!serverHost.endsWith("/")) {
            serverHost = serverHost + "/";
        }
        this.serverHost = serverHost;

        // Create a new RestTemplate instance
        this.restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);

        // Add the String message converter
        restTemplate.getMessageConverters().add(jackson2HttpMessageConverter);
        restTemplate.getMessageConverters().add(stringHttpMessageConverter);

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                String message = getStringFromInputStream(response.getBody());
                logger.error(message);
                throw new NaraRestClientException(message);
            }
        });
    }

    // convert InputStream to String
    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    /**
     *
     * @param requestBuilder
     * @param <T>
     * @return
     */
    @Override
    public <T> T sendAndRecieve(RequestBuilder requestBuilder)  {
        //
        String url = requestBuilder.getUrl();
        String httpMethod = requestBuilder.getMethod();

        Object request = requestBuilder.getRequestBody();
        Class clazz = requestBuilder.getResponseType();
        if (jSessionId != null) {
            requestBuilder.addHeader("Cookie", JSESSIONID + "=" + jSessionId);
        }

        HttpHeaders headers = requestBuilder.getHeaders();
        Map<String, String> pathParams = requestBuilder.getPathParams();
        MultiValueMap<String, String> queryParams = requestBuilder.getQueryParams();

        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);

        String httpUrl = serverHost + url;
        if (pathParams != null) {
            Set<String> keys = pathParams.keySet();
            for(String key : keys) {
                httpUrl = httpUrl.replace("{" + key + "}", pathParams.get(key));
            }
        }

        if (queryParams != null) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl);
            builder.queryParams(queryParams);
            httpUrl = builder.build().toUriString();
        }

        ResponseEntity<T> responseEntity = restTemplate.exchange(httpUrl, HttpMethod.valueOf(httpMethod), requestEntity, clazz);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        requestBuilder.setRespHeaders(responseHeaders);
        List<String> cookies = responseHeaders.get("Set-Cookie");
        if (cookies != null) {
            for (String cookie: cookies) {
                String[] cookieEntries = cookie.split(";");
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

        return responseEntity.getBody();

    }

}
