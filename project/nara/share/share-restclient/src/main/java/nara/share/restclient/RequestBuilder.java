package nara.share.restclient;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestBuilder {
    //
    private NaraRestUrl restUrl;
    private HttpHeaders headers;
    private HttpHeaders respHeaders;
    private Map<String, String> pathParams;
    private MultiValueMap<String, String> queryParams;
    private Class responseType;
    private Object requestBody;

    public static RequestBuilder create(NaraRestUrl restUrl) {
        return new RequestBuilder(restUrl);
    }

    private RequestBuilder(NaraRestUrl restUrl) {
        this.restUrl = restUrl;
    }

    public RequestBuilder addHeader(String key, String value) {
        if (this.headers == null) this.headers = new HttpHeaders();
        this.headers.add(key, value);
        return this;
    }

    public RequestBuilder addPathParam(String key, String value) {
        if (this.pathParams == null) this.pathParams = new HashMap<>();
        this.pathParams.put(key, value);
        return this;
    }

    public RequestBuilder addPathParam(String key, int value) {
        return addPathParam(key, value + "");
    }

    public RequestBuilder addQueryParam(String key, String value) {
        if (this.queryParams == null) this.queryParams = new LinkedMultiValueMap<>();
        this.queryParams.add(key, value);
        return this;
    }

    public RequestBuilder addQueryParam(String key, int value) {
        return addQueryParam(key, value + "");
    }

    public RequestBuilder addQueryParams(String key, List<String> values) {
        if (this.queryParams == null) this.queryParams = new LinkedMultiValueMap<>();
        this.queryParams.put(key, values);
        return this;
    }

    public RequestBuilder setResponseType(Class clazz) {
        this.responseType = clazz;
        return this;
    }

    public RequestBuilder setRequestBody(Object requestObject) {
        this.requestBody = requestObject;
        return this;
    }

    @Deprecated
    public RequestBuilder setRequestDto(Object requestDto) {
        this.requestBody = requestDto;
        return this;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public MultiValueMap<String, String> getQueryParams() {
        return queryParams;
    }

    public Class getResponseType() {
        return responseType;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public NaraRestUrl getRestUrl() {
        return restUrl;
    }

    public String getUrl() {
        return restUrl.getUrl();
    }

    public String getMethod() {
        return restUrl.getMethod().name();
    }

    public String getRespHeaderValue(String headerName) {
        if (respHeaders == null) return null;
        return respHeaders.getFirst(headerName);
    }

    public void setRespHeaders(HttpHeaders respHeaders) {
        this.respHeaders = respHeaders;
    }
}
