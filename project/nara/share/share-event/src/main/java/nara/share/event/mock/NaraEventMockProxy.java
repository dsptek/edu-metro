package nara.share.event.mock;

import nara.share.event.NaraEvent;
import nara.share.event.NaraEventProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NaraEventMockProxy implements NaraEventProxy<NaraEvent> {
    //
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void create(NaraEvent event) {
        logger.debug("======= Event Created ======");
        logger.debug(event.toString());
        logger.debug("============================");
    }
}
