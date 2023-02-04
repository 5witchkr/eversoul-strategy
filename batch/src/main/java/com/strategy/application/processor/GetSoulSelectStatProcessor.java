package com.strategy.application.processor;


import com.strategy.adapter.outbound.persistence.StatisticSoulselect;
import com.strategy.application.port.inbound.outputdto.SoulSelectResponseDto;
import com.strategy.application.port.inbound.portstat.GetSoulSelectStatInboundPort;
import com.strategy.application.port.outbound.StatisticSoulSelectOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetSoulSelectStatProcessor implements GetSoulSelectStatInboundPort {

    private final StatisticSoulSelectOutboundPort statisticSoulSelectOutboundPort;

    public GetSoulSelectStatProcessor(StatisticSoulSelectOutboundPort statisticSoulSelectOutboundPort) {
        this.statisticSoulSelectOutboundPort = statisticSoulSelectOutboundPort;
    }

    @Override
    public List<SoulSelectResponseDto> getTopRating(int argNumber) {
        return statisticSoulSelectOutboundPort.findAll()
                .stream()
                .sorted(Comparator.comparingInt(StatisticSoulselect::getSelectCount).reversed())
                .limit(argNumber)
                .map(this::statSoulSelectToSoulSelectResDto)
                .collect(Collectors.toList());
    }

    @Override
    public SoulSelectResponseDto getOne(Long id) {
        return statSoulSelectToSoulSelectResDto(statisticSoulSelectOutboundPort.getReferenceById(id));
    }

    @Override
    public List<SoulSelectResponseDto> getBottomRating(int argNumber) {
        return statisticSoulSelectOutboundPort.findAll()
                .stream()
                .sorted(Comparator.comparingInt(StatisticSoulselect::getSelectCount))
                .limit(argNumber)
                .map(this::statSoulSelectToSoulSelectResDto)
                .collect(Collectors.toList());
    }

    private SoulSelectResponseDto statSoulSelectToSoulSelectResDto(StatisticSoulselect statisticSoulselect){
        SoulSelectResponseDto.SoulSelectResponseDtoBuilder
                soulSelectResponseDtoBuilder = SoulSelectResponseDto.builder();
        soulSelectResponseDtoBuilder.id(statisticSoulselect.getId());
        soulSelectResponseDtoBuilder.selectCount(statisticSoulselect.getSelectCount());
        return soulSelectResponseDtoBuilder.build();
    }
}
