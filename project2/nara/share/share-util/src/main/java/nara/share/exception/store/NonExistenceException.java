package nara.share.exception.store;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class NonExistenceException extends NaraStoreException {

    public NonExistenceException(String message) {
        super(message);
    }

    public NonExistenceException(String message, Throwable t) {
        super(message, t);
    }

    public NonExistenceException(Throwable t) {
        super(t);
    }
}
