package com.strategy.application.facade;

import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;
import com.strategy.application.processor.SoulManagementProcessor;
import org.springframework.stereotype.Service;

@Service
public class SoulManagementFacade {

    private final SoulManagementProcessor soulManagementProcessor;

    public SoulManagementFacade(SoulManagementProcessor soulManagementProcessor) {
        this.soulManagementProcessor = soulManagementProcessor;
    }


    public void saveSoul(SoulSaveRequestDto soulSaveRequestDto) {
        soulManagementProcessor.saveSoul(soulSaveRequestDto);
    }

    public void deleteSoul(Long id) {
        soulManagementProcessor.deleteSoul(id);
    }

    public void putSoul(SoulPutRequestDto soulPutRequestDto) {
        soulManagementProcessor.putSoul(soulPutRequestDto);
    }
}
