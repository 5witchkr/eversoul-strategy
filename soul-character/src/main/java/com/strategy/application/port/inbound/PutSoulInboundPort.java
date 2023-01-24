package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;

public interface PutSoulInboundPort {

    void putSoul(SoulPutRequestDto soulPutRequestDto);
}
