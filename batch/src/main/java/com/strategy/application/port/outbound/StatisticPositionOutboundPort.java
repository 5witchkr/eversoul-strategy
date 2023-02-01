package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.StatisticPosition;

public interface StatisticPositionOutboundPort {


    StatisticPosition getReferenceById(Long id);

    void save(StatisticPosition statisticPosition);
}
