package com.strategy.application.processor.subscribe;

import com.strategy.application.port.inbound.portsubevent.TacticSoulSubDeleteEventInboundPort;
import com.strategy.application.port.outbound.TacticSoulcharacterOutboundPort;
import com.strategy.eventmodel.souldeleteevent.SoulCharacterTacticSoulDeleteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class TacticSoulCharacterDeleteSub implements TacticSoulSubDeleteEventInboundPort {

    private final TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort;

    public TacticSoulCharacterDeleteSub(TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort) {
        this.tacticSoulcharacterOutboundPort = tacticSoulcharacterOutboundPort;
    }


    @Override
    @EventListener
    public void deleteSoul(SoulCharacterTacticSoulDeleteEvent soulCharacterTacticSoulDeleteEvent) {
        tacticSoulcharacterOutboundPort
                .deleteById(soulCharacterTacticSoulDeleteEvent.getId());
    }
}
