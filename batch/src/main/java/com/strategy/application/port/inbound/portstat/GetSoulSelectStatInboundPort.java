package com.strategy.application.port.inbound.portstat;

import com.strategy.application.port.inbound.outputdto.SoulSelectResponseDto;

import java.util.List;

public interface GetSoulSelectStatInboundPort {

    List<SoulSelectResponseDto> getTopLate(int argNumber);

    SoulSelectResponseDto getOne(Long id);
}
