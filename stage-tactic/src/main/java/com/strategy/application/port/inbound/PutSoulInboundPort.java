package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.SoulPutDto;

public interface PutSoulInboundPort {
    void putSoul(SoulPutDto soulPutDto);
}
