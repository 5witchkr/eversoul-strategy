package com.strategy.application.processor.subscribe;


import com.strategy.application.port.inbound.portsubevent.StorySoulSubDeleteEventInboundPort;
import com.strategy.application.port.outbound.StorySoulcharacterOutboundPort;
import com.strategy.eventmodel.souldeleteevent.SoulCharacterStorySoulDeleteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StorySoulCharacterDeleteSub implements StorySoulSubDeleteEventInboundPort {

    private final StorySoulcharacterOutboundPort storySoulcharacterOutboundPort;

    public StorySoulCharacterDeleteSub(StorySoulcharacterOutboundPort storySoulcharacterOutboundPort) {
        this.storySoulcharacterOutboundPort = storySoulcharacterOutboundPort;
    }

    @Override
    @EventListener
    public void deleteSoul(SoulCharacterStorySoulDeleteEvent soulCharacterStorySoulDeleteEvent) {
        storySoulcharacterOutboundPort.deleteById(
                soulCharacterStorySoulDeleteEvent.getId());
    }
}
