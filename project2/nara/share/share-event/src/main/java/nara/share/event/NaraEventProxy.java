package nara.share.event;

public interface NaraEventProxy<T> {
    //
    void create(T event);
}
