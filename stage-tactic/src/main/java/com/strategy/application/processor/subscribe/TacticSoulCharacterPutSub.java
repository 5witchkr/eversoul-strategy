package com.strategy.application.processor.subscribe;

import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.application.port.inbound.portsubevent.TacticSoulSubPutEventInboundPort;
import com.strategy.application.port.outbound.TacticSoulcharacterOutboundPort;
import com.strategy.eventmodel.soulputevent.SoulCharacterTacticSoulPutEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class TacticSoulCharacterPutSub implements TacticSoulSubPutEventInboundPort {

    private final TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort;

    public TacticSoulCharacterPutSub(TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort) {
        this.tacticSoulcharacterOutboundPort = tacticSoulcharacterOutboundPort;
    }

    @Override
    @EventListener
    public void putSoul(SoulCharacterTacticSoulPutEvent soulCharacterTacticSoulPutEvent) {
        TacticSoulcharacter tacticSoulcharacter = tacticSoulcharacterOutboundPort.findById(soulCharacterTacticSoulPutEvent.getId())
                .orElseThrow(() -> new NullPointerException("유효하지 않은 정령 정보"));
        tacticSoulcharacter.setName(soulCharacterTacticSoulPutEvent.getName());
        tacticSoulcharacter.setTier(soulCharacterTacticSoulPutEvent.getTier());
        tacticSoulcharacter.setType(soulCharacterTacticSoulPutEvent.getType());
        tacticSoulcharacterOutboundPort.save(tacticSoulcharacter);
    }
}
