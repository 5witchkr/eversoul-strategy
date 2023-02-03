package com.strategy.application.facade;


import com.strategy.application.port.inbound.portstat.GetPositionStatInboundPort;
import com.strategy.application.port.inbound.portstat.GetSoulSelectStatInboundPort;
import com.strategy.application.port.inbound.outputdto.PositionStatResponseDto;
import com.strategy.application.port.inbound.outputdto.SoulSelectResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticFacade implements StatisticPortFacade{

    private final GetPositionStatInboundPort getPositionStatInboundPort;
    private final GetSoulSelectStatInboundPort getSoulSelectStatInboundPort;

    public StatisticFacade(GetPositionStatInboundPort getPositionStatInboundPort, GetSoulSelectStatInboundPort getSoulSelectStatInboundPort) {
        this.getPositionStatInboundPort = getPositionStatInboundPort;
        this.getSoulSelectStatInboundPort = getSoulSelectStatInboundPort;
    }


    @Override
    public List<PositionStatResponseDto> getPositionStat() {
        return getPositionStatInboundPort.getPositionStat();
    }

    @Override
    public SoulSelectResponseDto getOneSoulSelect(Long soulId) {
        return getSoulSelectStatInboundPort.getOne(soulId);
    }

    @Override
    public List<SoulSelectResponseDto> getTopSoulSelect(int argNumber) {
        return getSoulSelectStatInboundPort.getTopLate(argNumber);
    }
}
