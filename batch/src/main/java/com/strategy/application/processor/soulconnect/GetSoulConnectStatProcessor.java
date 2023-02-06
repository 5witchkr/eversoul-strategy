package com.strategy.application.processor.soulconnect;

import com.strategy.adapter.outbound.persistence.entity.StatisticSoulconnect;
import com.strategy.application.port.inbound.outputdto.SoulConnectResponseDto;
import com.strategy.application.port.inbound.portstat.GetSoulConnectStatInboundPort;
import com.strategy.application.port.outbound.StatisticSoulconnectOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class GetSoulConnectStatProcessor implements GetSoulConnectStatInboundPort {

    private final StatisticSoulconnectOutboundPort statisticSoulconnectOutboundPort;

    public GetSoulConnectStatProcessor(StatisticSoulconnectOutboundPort statisticSoulconnectOutboundPort) {
        this.statisticSoulconnectOutboundPort = statisticSoulconnectOutboundPort;
    }


    @Override
    public List<SoulConnectResponseDto> getTopRating(int argNumber) {
        return statisticSoulconnectOutboundPort.findTop10ByOrderByConnectCountDesc()
                .orElseThrow(() -> new NullPointerException("현재 데이터가 없습니다"))
                .stream()
                .map(this::soulConnectToSoulConnectResponseDto)
                .limit(argNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<SoulConnectResponseDto> getOneTopRating(Long soulId) {
         return Stream.of(statisticSoulconnectOutboundPort
                                 .findTop10ByConnectSoul(soulId)
                                 .orElse(Collections.emptyList()),
                         statisticSoulconnectOutboundPort
                                 .findTop10ByConnectedSoul(soulId)
                                 .orElse(Collections.emptyList()))
                 .flatMap(Collection::stream)
                 .sorted(Comparator.comparing(StatisticSoulconnect::getConnectCount).reversed())
                 .map(this::soulConnectToSoulConnectResponseDto)
                 .collect(Collectors.toList());
    }

    private SoulConnectResponseDto soulConnectToSoulConnectResponseDto(StatisticSoulconnect statisticSoulconnect){
        SoulConnectResponseDto.SoulConnectResponseDtoBuilder
                soulConnectResponseDto = SoulConnectResponseDto.builder();
        soulConnectResponseDto.connectSoul(statisticSoulconnect.getConnectSoul());
        soulConnectResponseDto.connectedSoul(statisticSoulconnect.getConnectedSoul());
        soulConnectResponseDto.connectCount(statisticSoulconnect.getConnectCount());
        return soulConnectResponseDto.build();
    }
}
