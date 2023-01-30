package com.strategy.application.processor.subscribe;


import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import com.strategy.application.port.inbound.portsubevent.StorySoulSubPutEventInboundPort;
import com.strategy.application.port.outbound.StorySoulcharacterOutboundPort;
import com.strategy.eventmodel.soulputevent.SoulCharacterStorySoulPutEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StorySoulCharacterPutSub  implements StorySoulSubPutEventInboundPort {

    private final StorySoulcharacterOutboundPort storySoulcharacterOutboundPort;

    public StorySoulCharacterPutSub(StorySoulcharacterOutboundPort storySoulcharacterOutboundPort) {
        this.storySoulcharacterOutboundPort = storySoulcharacterOutboundPort;
    }

    @Override
    @EventListener
    public void putSoul(SoulCharacterStorySoulPutEvent soulCharacterStorySoulPutEvent) {
        StorySoulcharacter storySoulcharacter = storySoulcharacterOutboundPort.findById(soulCharacterStorySoulPutEvent.getId())
                .orElseThrow(() -> new NullPointerException("유효하지 않은 정령 정보"));
        storySoulcharacter.setName(soulCharacterStorySoulPutEvent.getName());
        storySoulcharacter.setTier(soulCharacterStorySoulPutEvent.getTier());
        storySoulcharacter.setType(soulCharacterStorySoulPutEvent.getType());
        storySoulcharacterOutboundPort.save(storySoulcharacter);
    }
}
