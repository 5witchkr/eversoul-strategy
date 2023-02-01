package com.strategy.adapter.outbound.adapter;

import com.strategy.adapter.outbound.persistence.StatisticPosition;
import com.strategy.adapter.outbound.persistence.StatisticPositionRepository;
import com.strategy.application.port.outbound.StatisticPositionOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class StatisticPositionAdapter implements StatisticPositionOutboundPort {

    private final StatisticPositionRepository statisticPositionRepository;

    public StatisticPositionAdapter(StatisticPositionRepository statisticPositionRepository) {
        this.statisticPositionRepository = statisticPositionRepository;
    }


    @Override
    public StatisticPosition getReferenceById(Long id) {
        return statisticPositionRepository.getReferenceById(id);
    }

    @Override
    public void save(StatisticPosition statisticPosition) {
        statisticPositionRepository.save(statisticPosition);
    }

    @Override
    public List<StatisticPosition> findAll() {
        return statisticPositionRepository.findAll();
    }
}
