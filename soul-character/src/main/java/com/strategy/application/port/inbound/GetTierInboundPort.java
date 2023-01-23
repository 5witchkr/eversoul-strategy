package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;

import java.util.List;

public interface GetTierInboundPort {
    List<SoulCharacterResponseDto> getSoulByTier(String tier);
}