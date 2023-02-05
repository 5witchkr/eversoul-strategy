package com.strategy.application.port.inbound.portstat;

import com.strategy.application.port.inbound.outputdto.SoulConnectResponseDto;

import java.util.List;

public interface GetSoulConnectStatInboundPort {

    List<SoulConnectResponseDto> getTopRating(int argNumber);

    List<SoulConnectResponseDto> getOneTopRating(Long soulId);

}
