package com.strategy.application.facade;

import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;

import java.util.List;

public interface SoulCharacterPortFacade {

    List<SoulCharacterResponseDto> getSoulByTier(String tier);
}
