package nara.share.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class NaraEventListener<T> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private List<NaraEventHandler<T>> handlers = new ArrayList<>();

    private static ExecutorService executors = Executors.newFixedThreadPool(30);

    public void addHandler(NaraEventHandler handler) {
        //
        if (this.handlers == null) {
            this.handlers = new ArrayList<>();
        }
        this.handlers.add(handler);
    }

    public void listen(T t) {
        //
        logger.debug("Event received..." + t.getClass().getName());
        try {
            getHandlers().forEach(handler -> {
                executors.execute(() -> {
                    logger.debug(String.format("Nara event handler[%s] start...", handler.getClass().toString()));
                    handler.handle(t);
                    logger.debug(String.format("Nara event handler[%s] finish...", handler.getClass().toString()));
                });
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<NaraEventHandler<T>> getHandlers() {
        //
        return handlers;
    }
}
