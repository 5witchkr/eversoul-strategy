package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.StatisticSoulselect;

public interface StatisticSoulSelectOutboundPort {

    StatisticSoulselect getReferenceById(Long id);

    void save(StatisticSoulselect statisticSoulselect);
}
