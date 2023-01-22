package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.outputdto.TierDto;

public interface SoulCharacterInboundPort {
    TierDto getTier(Long tier);
}