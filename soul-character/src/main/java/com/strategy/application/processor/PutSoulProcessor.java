package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.SoulCharacter;
import com.strategy.application.port.inbound.PutSoulInboundPort;
import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.outbound.SoulManagementOutboundPort;
import org.springframework.stereotype.Component;

@Component
public class PutSoulProcessor implements PutSoulInboundPort {

    private final SoulManagementOutboundPort soulManagementOutboundPort;

    public PutSoulProcessor(SoulManagementOutboundPort soulManagementOutboundPort) {
        this.soulManagementOutboundPort = soulManagementOutboundPort;
    }

    @Override
    public void putSoul(SoulPutRequestDto soulPutRequestDto) {
        SoulCharacter soulCharacter = soulManagementOutboundPort.findById(soulPutRequestDto.getId())
                .orElseThrow(() -> new NullPointerException("유효하지 않은 입력"));
        soulCharacter.setName(soulPutRequestDto.getName());
        soulCharacter.setTier(soulPutRequestDto.getTier());
        soulCharacter.setType(soulPutRequestDto.getType());
        soulManagementOutboundPort.save(soulCharacter);
    }
}
