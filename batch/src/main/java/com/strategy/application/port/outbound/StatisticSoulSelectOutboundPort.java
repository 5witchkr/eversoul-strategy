package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.StatisticSoulselect;

public interface StatisticSoulSelectOutboundPort {

    StatisticSoulselect getReferenceById(Long l);

    void save(StatisticSoulselect statisticSoulselect);
}
