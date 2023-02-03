package com.strategy.application.facade;

import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;

public interface SoulManagementPortFacade {


    void saveSoul(SoulSaveRequestDto soulSaveRequestDto);

    void deleteSoul(Long id);

    void putSoul(SoulPutRequestDto soulPutRequestDto);
}
