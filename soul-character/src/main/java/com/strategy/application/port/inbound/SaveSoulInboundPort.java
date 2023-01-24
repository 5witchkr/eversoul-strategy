package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;

public interface SaveSoulInboundPort {

    void saveSoul(SoulSaveRequestDto soulSaveRequestDto);
}
