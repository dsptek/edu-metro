package nara.share.event.memory;

import nara.share.event.NaraEvent;
import nara.share.event.NaraEventProxy;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEventQueueProxy implements NaraEventProxy<NaraEvent> {
    //
    private Map<String, InMemoryEventQueue> queueMap;

    public InMemoryEventQueueProxy() {
        //
        queueMap = new HashMap<>();
    }

    @Override
    public void create(NaraEvent event) {
        //
        String queueName = event.getClass().getTypeName();
        InMemoryEventQueue queue = this.queueMap.get(queueName);
        if (queue != null) queue.add(event);
    }

    public void addEventQueue(String name, InMemoryEventQueue eventQueue) {
        //
        this.queueMap.put(name, eventQueue);
    }
}
