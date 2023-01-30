package com.strategy.application.port.inbound.portsoul;

import com.strategy.application.port.inbound.inputdto.souldto.SoulPutDto;

public interface PutTacticSoulInboundPort {
    void putSoul(SoulPutDto soulPutDto);
}
