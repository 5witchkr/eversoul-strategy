package com.strategy.application.facade;

import com.strategy.application.port.inbound.outputdto.PositionStatResponseDto;
import com.strategy.application.port.inbound.outputdto.SoulConnectResponseDto;
import com.strategy.application.port.inbound.outputdto.SoulSelectResponseDto;

import java.util.List;

public interface StatisticPortFacade {


    List<PositionStatResponseDto> getPositionStat();

    SoulSelectResponseDto getOneSoulSelect(Long soulId);

    List<SoulSelectResponseDto> getSoulSelect(int argNumber, String rating);

    List<SoulConnectResponseDto> getTopSoulConnect(int argNumber);

    List<SoulConnectResponseDto> getOneSoulConnect(Long soulId);
}
