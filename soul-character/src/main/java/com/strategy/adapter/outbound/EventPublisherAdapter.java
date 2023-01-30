package com.strategy.adapter.outbound;

import com.strategy.application.port.outbound.EventPublisherOutboundPort;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class EventPublisherAdapter implements EventPublisherOutboundPort {

    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishEvent(Object event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
