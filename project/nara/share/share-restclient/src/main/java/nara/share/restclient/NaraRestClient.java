package nara.share.restclient;

public interface NaraRestClient {
    //
    <T> T sendAndRecieve(RequestBuilder requestBuilder);
}
