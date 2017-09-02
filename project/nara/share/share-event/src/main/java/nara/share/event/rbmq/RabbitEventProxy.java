package nara.share.event.rbmq;

import nara.share.event.NaraEventProxy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitEventProxy implements NaraEventProxy<RabbitEvent> {
    //
    private RabbitTemplate rabbitTemplate;

    public RabbitEventProxy(RabbitTemplate rabbitTemplate) {
        //
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void create(RabbitEvent rabbitEvent) {
        //
        rabbitTemplate.convertAndSend(rabbitEvent.getTopicExchange(), rabbitEvent.getRoutingKey(), rabbitEvent);
    }
}
