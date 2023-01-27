package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.SoulCharacter;
import com.strategy.application.port.inbound.SaveSoulInboundPort;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;
import com.strategy.application.port.outbound.SoulManagementOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class SaveSoulProcessor implements SaveSoulInboundPort {

    private final SoulManagementOutboundPort soulManagementOutboundPort;

    public SaveSoulProcessor(SoulManagementOutboundPort soulManagementOutboundPort) {
        this.soulManagementOutboundPort = soulManagementOutboundPort;
    }

    @Override
    public void saveSoul(SoulSaveRequestDto soulSaveRequestDto) {
        SoulCharacter soulCharacter = SoulCharacter.builder()
                .tier(soulSaveRequestDto.getTier())
                .name(soulSaveRequestDto.getName())
                .type(soulSaveRequestDto.getType())
                .build();
        soulManagementOutboundPort.save(soulCharacter);
    }
}
