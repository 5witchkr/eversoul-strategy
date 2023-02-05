package com.strategy.adapter.outbound.adapter;

import com.strategy.adapter.outbound.persistence.StatisticSoulConnectRepository;
import com.strategy.adapter.outbound.persistence.StatisticSoulconnect;
import com.strategy.application.port.outbound.StatisticSoulconnectOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class StatisticSoulconnectAdapter implements StatisticSoulconnectOutboundPort {

    private final StatisticSoulConnectRepository statisticSoulConnectRepository;

    public StatisticSoulconnectAdapter(StatisticSoulConnectRepository statisticSoulConnectRepository) {
        this.statisticSoulConnectRepository = statisticSoulConnectRepository;
    }

    @Override
    public Optional<StatisticSoulconnect> findByConnectSoulAndConnectedSoul(Long connectSoul, Long connectedSoul) {
        return statisticSoulConnectRepository.findByConnectSoulAndConnectedSoul(connectSoul, connectedSoul);
    }

    @Override
    public StatisticSoulconnect save(StatisticSoulconnect statisticSoulconnect) {
        return statisticSoulConnectRepository.save(statisticSoulconnect);
    }
}
