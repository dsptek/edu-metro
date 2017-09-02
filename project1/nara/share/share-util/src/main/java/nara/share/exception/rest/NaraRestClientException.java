package nara.share.exception.rest;

import nara.share.exception.NaraException;

/**
 * Created by kchuh@nextree.co.kr on 2016. 6. 23..
 */
public class NaraRestClientException extends NaraException {
    //
    public NaraRestClientException(String message) {
        super(message);
    }

    public NaraRestClientException(String message, Throwable t) {
        super(message, t);
    }

    public NaraRestClientException(Throwable t) {
        super(t);
    }
}
