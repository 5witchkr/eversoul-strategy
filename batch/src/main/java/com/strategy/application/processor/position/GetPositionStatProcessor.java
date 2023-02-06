package com.strategy.application.processor.position;


import com.strategy.adapter.outbound.persistence.entity.StatisticPosition;
import com.strategy.application.port.inbound.outputdto.PositionStatResponseDto;
import com.strategy.application.port.inbound.portstat.GetPositionStatInboundPort;
import com.strategy.application.port.outbound.StatisticPositionOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetPositionStatProcessor implements GetPositionStatInboundPort {

    private final StatisticPositionOutboundPort statisticPositionOutboundPort;

    public GetPositionStatProcessor(StatisticPositionOutboundPort statisticPositionOutboundPort) {
        this.statisticPositionOutboundPort = statisticPositionOutboundPort;
    }

    @Override
    public List<PositionStatResponseDto> getPositionStat() {
        return statisticPositionOutboundPort.findAll()
                .stream()
                .sorted(Comparator.comparingInt(StatisticPosition::getPositionCount).reversed())
                .map(this::statisticPositionToPositionStatResDto)
                .collect(Collectors.toList());
    }

    private PositionStatResponseDto statisticPositionToPositionStatResDto(StatisticPosition statisticPosition){
        PositionStatResponseDto.PositionStatResponseDtoBuilder
                positionStatResponseDtoBuilder = PositionStatResponseDto.builder();
        positionStatResponseDtoBuilder.positionName(statisticPosition.getPositionName());
        positionStatResponseDtoBuilder.positionCount(statisticPosition.getPositionCount());
        return positionStatResponseDtoBuilder.build();
    }
}
