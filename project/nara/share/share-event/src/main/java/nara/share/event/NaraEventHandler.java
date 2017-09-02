package nara.share.event;

public interface NaraEventHandler<T> {
    //
    void handle(T event);
}
