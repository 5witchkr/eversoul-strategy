package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.SoulCharacter;
import com.strategy.application.port.inbound.DeleteSoulInboundPort;
import com.strategy.application.port.inbound.PutSoulInboundPort;
import com.strategy.application.port.inbound.SaveSoulInboundPort;
import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;
import com.strategy.application.port.outbound.SoulManagementOutboundPort;
import org.springframework.stereotype.Component;

@Component
public class SoulTierManagementProcessor implements SaveSoulInboundPort, PutSoulInboundPort, DeleteSoulInboundPort {

    private final SoulManagementOutboundPort soulManagementOutboundPort;

    public SoulTierManagementProcessor(SoulManagementOutboundPort soulManagementOutboundPort) {
        this.soulManagementOutboundPort = soulManagementOutboundPort;
    }

    @Override
    public void deleteSoul(Long id) {
        soulManagementOutboundPort.deleteById(id);
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
