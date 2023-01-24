package com.strategy.application.processor;

import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.application.port.inbound.DeleteSoulInboundPort;
import com.strategy.application.port.inbound.PutSoulInboundPort;
import com.strategy.application.port.inbound.SaveSoulInboundPort;
import com.strategy.application.port.inbound.inputdto.SoulPutDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveDto;
import com.strategy.application.port.outbound.TacticSoulcharacterOutboundPort;
import org.springframework.stereotype.Component;

@Component
public class SoulManagementProcessor implements SaveSoulInboundPort, PutSoulInboundPort, DeleteSoulInboundPort {

    private final TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort;

    public SoulManagementProcessor(TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort) {
        this.tacticSoulcharacterOutboundPort = tacticSoulcharacterOutboundPort;
    }

    @Override
    public void deleteSoul(Long id) {
        tacticSoulcharacterOutboundPort.deleteById(id);
    }

    @Override
    public void putSoul(SoulPutDto soulPutDto) {
        TacticSoulcharacter tacticSoulcharacter = tacticSoulcharacterOutboundPort.findById(soulPutDto.getId())
                .orElseThrow(() -> new NullPointerException("유효하지 보은 정보"));
        tacticSoulcharacter.setName(soulPutDto.getName());
        tacticSoulcharacter.setTier(soulPutDto.getTier());
        tacticSoulcharacter.setType(soulPutDto.getType());
        tacticSoulcharacterOutboundPort.save(tacticSoulcharacter);
    }

    @Override
    public void saveSoul(SoulSaveDto soulSaveDto) {
        TacticSoulcharacter tacticSoulcharacter = TacticSoulcharacter.builder()
                .type(soulSaveDto.getType())
                .tier(soulSaveDto.getTier())
                .name(soulSaveDto.getName())
                .build();
        tacticSoulcharacterOutboundPort.save(tacticSoulcharacter);
    }
}
