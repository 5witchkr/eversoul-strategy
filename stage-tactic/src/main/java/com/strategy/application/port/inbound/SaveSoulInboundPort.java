package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.SoulSaveDto;

public interface SaveSoulInboundPort {
    void saveSoul(SoulSaveDto soulSaveDto);
}
