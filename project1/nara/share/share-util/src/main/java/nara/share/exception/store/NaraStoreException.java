package nara.share.exception.store;

import nara.share.exception.NaraException;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class NaraStoreException extends NaraException {

    public NaraStoreException(String message) {
        super(message);
    }

    public NaraStoreException(String message, Throwable t) {
        super(message, t);
    }

    public NaraStoreException(Throwable t) {
        super(t);
    }
}
