package com.strategy.application.facade;


import com.strategy.application.port.inbound.outputdto.SoulConnectResponseDto;
import com.strategy.application.port.inbound.portstat.GetPositionStatInboundPort;
import com.strategy.application.port.inbound.portstat.GetSoulConnectStatInboundPort;
import com.strategy.application.port.inbound.portstat.GetSoulSelectStatInboundPort;
import com.strategy.application.port.inbound.outputdto.PositionStatResponseDto;
import com.strategy.application.port.inbound.outputdto.SoulSelectResponseDto;
import com.strategy.application.validator.StatSelectNumberValidator;
import com.strategy.application.validator.StatSoulIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StatisticFacade implements StatisticPortFacade{

    private final GetPositionStatInboundPort getPositionStatInboundPort;
    private final GetSoulSelectStatInboundPort getSoulSelectStatInboundPort;
    private final GetSoulConnectStatInboundPort getSoulConnectStatInboundPort;
    private final StatSelectNumberValidator statSelectNumberValidator;
    private final StatSoulIdValidator statSoulIdValidator;

    public StatisticFacade(GetPositionStatInboundPort getPositionStatInboundPort,
                           GetSoulSelectStatInboundPort getSoulSelectStatInboundPort,
                           GetSoulConnectStatInboundPort getSoulConnectStatInboundPort,
                           StatSelectNumberValidator statSelectNumberValidator,
                           StatSoulIdValidator statSoulIdValidator) {
        this.getPositionStatInboundPort = getPositionStatInboundPort;
        this.getSoulSelectStatInboundPort = getSoulSelectStatInboundPort;
        this.getSoulConnectStatInboundPort = getSoulConnectStatInboundPort;
        this.statSelectNumberValidator = statSelectNumberValidator;
        this.statSoulIdValidator = statSoulIdValidator;
    }


    @Override
    public List<PositionStatResponseDto> getPositionStat() {
        return getPositionStatInboundPort.getPositionStat();
    }

    @Override
    public SoulSelectResponseDto getOneSoulSelect(Long soulId) {
        statSoulIdValidator.checkSoulId(soulId);
        return getSoulSelectStatInboundPort.getOne(soulId);
    }

    @Override
    public List<SoulSelectResponseDto> getSoulSelect(int argNumber, String rating) {
        statSelectNumberValidator.checkSelectNumber(argNumber);

        if (Objects.equals(rating, "bottom")) {
            return getSoulSelectStatInboundPort.getBottomRating(argNumber);
        }
        return getSoulSelectStatInboundPort.getTopRating(argNumber);
    }

    @Override
    public List<SoulConnectResponseDto> getTopSoulConnect(int argNumber) {
        return getSoulConnectStatInboundPort.getTopRating(argNumber);
    }

    @Override
    public List<SoulConnectResponseDto> getOneSoulConnect(Long soulId) {
        return getSoulConnectStatInboundPort.getOneTopRating(soulId);
    }
}
