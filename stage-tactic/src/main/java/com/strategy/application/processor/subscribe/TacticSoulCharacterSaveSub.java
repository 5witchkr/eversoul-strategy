package com.strategy.application.processor.subscribe;

import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.application.port.inbound.portsubevent.TacticSoulSubSaveEventInboundPort;
import com.strategy.application.port.outbound.TacticSoulcharacterOutboundPort;
import com.strategy.eventmodel.soulsaveevent.SoulCharacterTacticSoulSaveEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class TacticSoulCharacterSaveSub implements TacticSoulSubSaveEventInboundPort {

    private final TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort;

    public TacticSoulCharacterSaveSub(TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort) {
        this.tacticSoulcharacterOutboundPort = tacticSoulcharacterOutboundPort;
    }

    @Override
    @EventListener
    public void saveSoul(SoulCharacterTacticSoulSaveEvent soulCharacterTacticSoulSaveEvent) {
        TacticSoulcharacter tacticSoulcharacter =
                TacticSoulcharacter.builder()
                .name(soulCharacterTacticSoulSaveEvent.getName())
                .tier(soulCharacterTacticSoulSaveEvent.getTier())
                .type(soulCharacterTacticSoulSaveEvent.getType())
                .build();
        tacticSoulcharacterOutboundPort.save(tacticSoulcharacter);
    }
}
