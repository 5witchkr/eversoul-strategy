package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import com.strategy.application.port.inbound.StorySoulManagementInboundPort;
import com.strategy.application.port.inbound.inputdto.StorySoulSaveDto;
import com.strategy.application.port.outbound.StorySoulcharacterOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class StorySoulManagementProcessor implements StorySoulManagementInboundPort {

    private final StorySoulcharacterOutboundPort storySoulcharacterOutboundPort;

    public StorySoulManagementProcessor(StorySoulcharacterOutboundPort storySoulcharacterOutboundPort) {
        this.storySoulcharacterOutboundPort = storySoulcharacterOutboundPort;
    }

    @Override
    public void postStorySoul(StorySoulSaveDto soulSaveDto) {

        StorySoulcharacter storySoulcharacter = StorySoulcharacter.builder()
                .name(soulSaveDto.getName())
                .type(soulSaveDto.getType())
                .tier(soulSaveDto.getTier())
                .build();
        storySoulcharacterOutboundPort.save(storySoulcharacter);
    }

    @Override
    public void deleteStorySoul(Long id) {
        storySoulcharacterOutboundPort.deleteById(id);
    }
}
