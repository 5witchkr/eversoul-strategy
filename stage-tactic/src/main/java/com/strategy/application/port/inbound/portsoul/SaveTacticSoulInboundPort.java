package com.strategy.application.port.inbound.portsoul;

import com.strategy.application.port.inbound.inputdto.souldto.SoulSaveDto;

public interface SaveTacticSoulInboundPort {
    void saveSoul(SoulSaveDto soulSaveDto);
}
