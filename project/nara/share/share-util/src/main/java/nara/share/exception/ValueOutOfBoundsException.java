package nara.share.exception;

public class ValueOutOfBoundsException extends RuntimeException {
    //
    public ValueOutOfBoundsException(String message) {
        super(message);
    }

    public ValueOutOfBoundsException(String message, Throwable t) {
        super(message, t);
    }

    public ValueOutOfBoundsException(Throwable t) {
        super(t);
    }
}