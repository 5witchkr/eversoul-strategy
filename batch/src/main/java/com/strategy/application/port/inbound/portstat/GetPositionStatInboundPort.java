package com.strategy.application.port.inbound.portstat;

import com.strategy.application.port.inbound.outputdto.PositionStatResponseDto;

import java.util.List;

public interface GetPositionStatInboundPort {

    List<PositionStatResponseDto> getPositionStat();
}
