package nara.share.event;

public interface NaraEventQueue<T> {
    //
    T take();
    T poll();
    void add(T event);
}
