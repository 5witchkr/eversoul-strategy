package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.entity.StatisticSoulconnect;

import java.util.List;
import java.util.Optional;

public interface StatisticSoulconnectOutboundPort {
    Optional<StatisticSoulconnect> findByConnectSoulAndConnectedSoul(Long connectSoul, Long connectedSoul);

    StatisticSoulconnect save(StatisticSoulconnect statisticSoulconnect);

    Optional<List<StatisticSoulconnect>> findTop10ByConnectedSoul(Long connectedSoul);

    Optional<List<StatisticSoulconnect>> findTop10ByConnectSoul(Long connectSoul);

    Optional<List<StatisticSoulconnect>> findTop10ByOrderByConnectCountDesc();

}
