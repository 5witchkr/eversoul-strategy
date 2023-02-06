package com.strategy.adapter.outbound.adapter;

import com.strategy.adapter.outbound.persistence.jparepository.StatisticSoulSelectRepository;
import com.strategy.adapter.outbound.persistence.entity.StatisticSoulselect;
import com.strategy.application.port.outbound.StatisticSoulSelectOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;


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

    @Override
    public List<StatisticSoulselect> findAll() {
        return statisticSoulSelectRepository.findAll();
    }
}
