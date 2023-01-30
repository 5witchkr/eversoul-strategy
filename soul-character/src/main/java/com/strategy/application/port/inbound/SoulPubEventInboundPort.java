package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;

public interface SoulPubEventInboundPort {

    void publishSaveEvent(SoulSaveRequestDto soulSaveRequestDto);
    void publishPutEvent(SoulPutRequestDto soulPutRequestDto);
    void publishDeleteEvent(Long soulId);
}
