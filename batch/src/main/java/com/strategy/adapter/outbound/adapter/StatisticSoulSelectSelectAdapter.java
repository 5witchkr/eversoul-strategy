package com.strategy.adapter.outbound.adapter;

import com.strategy.adapter.outbound.persistence.StatisticSoulSelectRepository;
import com.strategy.adapter.outbound.persistence.StatisticSoulselect;
import com.strategy.application.port.outbound.StatisticSoulSelectOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class StatisticSoulSelectSelectAdapter implements StatisticSoulSelectOutboundPort {

    private final StatisticSoulSelectRepository statisticSoulSelectRepository;

    public StatisticSoulSelectSelectAdapter(StatisticSoulSelectRepository statisticSoulSelectRepository) {
        this.statisticSoulSelectRepository = statisticSoulSelectRepository;
    }

    @Override
    public StatisticSoulselect getReferenceById(Long id) {
        return statisticSoulSelectRepository.getReferenceById(id);
    }

    @Override
    public void save(StatisticSoulselect statisticSoulselect) {
        statisticSoulSelectRepository.save(statisticSoulselect);
    }
}
