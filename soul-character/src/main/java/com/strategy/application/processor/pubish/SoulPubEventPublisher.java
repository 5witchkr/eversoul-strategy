package com.strategy.application.processor.pubish;

import com.strategy.application.port.inbound.SoulPubEventInboundPort;
import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;
import com.strategy.application.port.outbound.EventPublisherOutboundPort;
import com.strategy.eventmodel.souldeleteevent.SoulCharacterStorySoulDeleteEvent;
import com.strategy.eventmodel.souldeleteevent.SoulCharacterTacticSoulDeleteEvent;
import com.strategy.eventmodel.soulputevent.SoulCharacterStorySoulPutEvent;
import com.strategy.eventmodel.soulputevent.SoulCharacterTacticSoulPutEvent;
import com.strategy.eventmodel.soulsaveevent.SoulCharacterStorySoulSaveEvent;
import com.strategy.eventmodel.soulsaveevent.SoulCharacterTacticSoulSaveEvent;
import org.springframework.stereotype.Component;


@Component
public class SoulPubEventPublisher implements SoulPubEventInboundPort {


    private final EventPublisherOutboundPort eventPublisher;

    public SoulPubEventPublisher(EventPublisherOutboundPort eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void publishSaveEvent(SoulSaveRequestDto soulSaveRequestDto) {
        eventPublisher.publishEvent(new SoulCharacterTacticSoulSaveEvent(
                soulSaveRequestDto.getName(),
                soulSaveRequestDto.getType(),
                soulSaveRequestDto.getTier()
        ));
        eventPublisher.publishEvent(new SoulCharacterStorySoulSaveEvent(
                soulSaveRequestDto.getName(),
                soulSaveRequestDto.getType(),
                soulSaveRequestDto.getTier()
        ));
    }

    @Override
    public void publishPutEvent(SoulPutRequestDto soulPutRequestDto) {
        eventPublisher.publishEvent(new SoulCharacterTacticSoulPutEvent(
                soulPutRequestDto.getId(),
                soulPutRequestDto.getName(),
                soulPutRequestDto.getType(),
                soulPutRequestDto.getTier()
        ));
        eventPublisher.publishEvent(new SoulCharacterStorySoulPutEvent(
                soulPutRequestDto.getId(),
                soulPutRequestDto.getName(),
                soulPutRequestDto.getType(),
                soulPutRequestDto.getTier()
        ));
    }

    @Override
    public void publishDeleteEvent(Long soulId) {
        eventPublisher.publishEvent(new SoulCharacterTacticSoulDeleteEvent(
                soulId
        ));
        eventPublisher.publishEvent(new SoulCharacterStorySoulDeleteEvent(
                soulId
        ));
    }
}
