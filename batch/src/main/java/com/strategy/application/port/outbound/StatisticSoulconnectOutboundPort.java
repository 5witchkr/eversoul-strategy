package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.StatisticSoulconnect;

import java.util.Optional;

public interface StatisticSoulconnectOutboundPort {
    Optional<StatisticSoulconnect> findByConnectSoulAndConnectedSoul(Long connectSoul, Long connectedSoul);

    StatisticSoulconnect save(StatisticSoulconnect statisticSoulconnect);
}
