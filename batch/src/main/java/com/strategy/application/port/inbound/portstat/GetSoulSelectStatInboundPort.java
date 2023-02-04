package com.strategy.application.port.inbound.portstat;

import com.strategy.application.port.inbound.outputdto.SoulSelectResponseDto;

import java.util.List;

public interface GetSoulSelectStatInboundPort {

    List<SoulSelectResponseDto> getTopRating(int argNumber);

    SoulSelectResponseDto getOne(Long id);

    List<SoulSelectResponseDto> getBottomRating(int argNumber);
}
