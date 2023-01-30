package com.strategy.application.port.outbound;

import org.springframework.context.ApplicationEvent;

public interface EventPublisherOutboundPort {


    void publishEvent(Object event);

    void publishEvent(ApplicationEvent event);
}
