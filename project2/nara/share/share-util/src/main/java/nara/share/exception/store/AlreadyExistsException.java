package nara.share.exception.store;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class AlreadyExistsException extends NaraStoreException {

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable t) {
        super(message, t);
    }

    public AlreadyExistsException(Throwable t) {
        super(t);
    }

}
