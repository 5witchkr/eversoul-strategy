package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.entity.StatisticSoulselect;

import java.util.List;

public interface StatisticSoulSelectOutboundPort {

    StatisticSoulselect getReferenceById(Long id);

    void save(StatisticSoulselect statisticSoulselect);

    List<StatisticSoulselect> findAll();
}
