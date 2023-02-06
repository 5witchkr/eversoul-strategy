package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.entity.StatisticPosition;

import java.util.List;

public interface StatisticPositionOutboundPort {


    StatisticPosition getReferenceById(Long id);

    void save(StatisticPosition statisticPosition);

    List<StatisticPosition> findAll();
}
