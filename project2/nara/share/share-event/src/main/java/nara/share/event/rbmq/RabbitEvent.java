package nara.share.event.rbmq;

import nara.share.event.NaraEvent;

public interface RabbitEvent extends NaraEvent {
    //
    String getTopicExchange();
    String getRoutingKey();
}
