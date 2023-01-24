package com.strategy.application.facade;

import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;
import com.strategy.application.processor.SoulTierManagementProcessor;
import org.springframework.stereotype.Service;

@Service
public class SoulManagementFacade {

    private final SoulTierManagementProcessor soulTierManagementProcessor;

    public SoulManagementFacade(SoulTierManagementProcessor soulTierManagementProcessor) {
        this.soulTierManagementProcessor = soulTierManagementProcessor;
    }


    public void saveSoul(SoulSaveRequestDto soulSaveRequestDto) {
        soulTierManagementProcessor.saveSoul(soulSaveRequestDto);
    }

    public void deleteSoul(Long id) {
        soulTierManagementProcessor.deleteSoul(id);
    }

    public void putSoul(SoulPutRequestDto soulPutRequestDto) {
        soulTierManagementProcessor.putSoul(soulPutRequestDto);
    }
}
