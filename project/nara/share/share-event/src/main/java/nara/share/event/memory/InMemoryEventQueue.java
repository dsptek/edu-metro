package nara.share.event.memory;

import nara.share.event.NaraEvent;
import nara.share.event.NaraEventQueue;
import nara.share.exception.NaraException;

import java.util.concurrent.ArrayBlockingQueue;

public class InMemoryEventQueue implements NaraEventQueue<NaraEvent> {
    //
    private ArrayBlockingQueue<NaraEvent> eventQueue;

    public InMemoryEventQueue() {
        //
        this(10000);
    }

    public InMemoryEventQueue(int capacity) {
        //
        this.eventQueue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public NaraEvent take() {
        //
        try {
            return this.eventQueue.take();
        } catch (InterruptedException e) {
            throw new NaraException(e.getMessage(), e);
        }
    }

    @Override
    public NaraEvent poll() {
        return this.eventQueue.poll();
    }

    @Override
    public void add(NaraEvent event) {
        //
        this.eventQueue.add(event);
    }

}
