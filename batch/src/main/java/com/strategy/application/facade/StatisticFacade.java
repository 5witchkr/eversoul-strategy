package com.strategy.application.facade;


import com.strategy.application.port.inbound.portstat.GetPositionStatInboundPort;
import com.strategy.application.port.inbound.portstat.GetSoulSelectStatInboundPort;
import com.strategy.application.port.inbound.outputdto.PositionStatResponseDto;
import com.strategy.application.port.inbound.outputdto.SoulSelectResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticFacade {

    private final GetPositionStatInboundPort getPositionStatInboundPort;
    private final GetSoulSelectStatInboundPort getSoulSelectStatInboundPort;

    public StatisticFacade(GetPositionStatInboundPort getPositionStatInboundPort, GetSoulSelectStatInboundPort getSoulSelectStatInboundPort) {
        this.getPositionStatInboundPort = getPositionStatInboundPort;
        this.getSoulSelectStatInboundPort = getSoulSelectStatInboundPort;
    }


    public List<PositionStatResponseDto> getPositionStat() {
        return getPositionStatInboundPort.getPositionStat();
    }

    public SoulSelectResponseDto getOneSoulSelect(Long soulId) {
        return getSoulSelectStatInboundPort.getOne(soulId);
    }

    public List<SoulSelectResponseDto> getTopSoulSelect(int argNumber) {
        return getSoulSelectStatInboundPort.getTopLate(argNumber);
    }
}
