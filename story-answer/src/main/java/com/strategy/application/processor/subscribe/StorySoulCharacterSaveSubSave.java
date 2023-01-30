package com.strategy.application.processor.subscribe;

import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import com.strategy.application.port.inbound.portsubevent.StorySoulSubSaveEventInboundPort;
import com.strategy.application.port.outbound.StorySoulcharacterOutboundPort;
import com.strategy.eventmodel.soulsaveevent.SoulCharacterStorySoulSaveEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class StorySoulCharacterSaveSubSave implements StorySoulSubSaveEventInboundPort {

    private final StorySoulcharacterOutboundPort storySoulcharacterOutboundPort;

    public StorySoulCharacterSaveSubSave(StorySoulcharacterOutboundPort storySoulcharacterOutboundPort) {
        this.storySoulcharacterOutboundPort = storySoulcharacterOutboundPort;
    }

    @Override
    @EventListener
    public void saveSoul(SoulCharacterStorySoulSaveEvent soulCharacterStorySoulSaveEvent) {
        StorySoulcharacter storySoulcharacter = StorySoulcharacter.builder()
                .name(soulCharacterStorySoulSaveEvent.getName())
                .tier(soulCharacterStorySoulSaveEvent.getTier())
                .type(soulCharacterStorySoulSaveEvent.getType())
                .build();
        storySoulcharacterOutboundPort.save(storySoulcharacter);
    }
}
