package nara.share.exception;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public class NaraException extends RuntimeException {

    public NaraException(String message) {
        super(message);
    }
    
    public NaraException(String message, Throwable t) {
        super(message, t);
    }

    public NaraException(Throwable t) {
        super(t);
    }

}
