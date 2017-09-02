package nara.share.event.memory;

import nara.share.event.NaraEvent;
import nara.share.event.NaraEventHandler;
import nara.share.event.NaraEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InMemoryEventQueueListener extends NaraEventListener<NaraEvent> implements Runnable {
    //
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private InMemoryEventQueue eventQueue;
    private ExecutorService executorService;

    public InMemoryEventQueueListener(InMemoryEventQueue eventQueue) {
        //
        this.eventQueue = eventQueue;
//        this.executorService = Executors.newFixedThreadPool(30); // default 30
    }

    public InMemoryEventQueueListener(InMemoryEventQueue eventQueue, int handlerThreadPoolSize) {
        //
        this.eventQueue = eventQueue;
        this.executorService = Executors.newFixedThreadPool(handlerThreadPoolSize);
    }

    @Override
    public void listen(NaraEvent event) {
        //
        logger.debug(String.format("Nara event received...[%s]", event.toString()));

        for(NaraEventHandler handler : getHandlers()) {

            if (executorService != null) executorService.execute(() -> executeHandler(handler, event));
            else executeHandler(handler, event);
        }
    }

    private void executeHandler(NaraEventHandler handler, NaraEvent event) {
        try {
            logger.debug(String.format("Nara event handler[%s] start...", handler.getClass().toString()));
            handler.handle(event);
            logger.debug(String.format("Nara event handler[%s] finished...", handler.getClass().toString()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void run() {
        //
        while(true) {
            //
            try {
                listen(eventQueue.take());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
