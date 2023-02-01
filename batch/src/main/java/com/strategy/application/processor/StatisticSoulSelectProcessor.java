package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.StatisticSoulselect;
import com.strategy.application.port.inbound.portbatch.StatisticSoulSelectInboundPort;
import com.strategy.application.port.outbound.StatisticSoulSelectOutboundPort;
import com.strategy.application.port.outbound.TacticCharacterOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class StatisticSoulSelectProcessor implements StatisticSoulSelectInboundPort {

    private final StatisticSoulSelectOutboundPort statisticSoulSelectOutboundPort;
    private final TacticCharacterOutboundPort tacticCharacterOutboundPort;

    public StatisticSoulSelectProcessor(StatisticSoulSelectOutboundPort statisticSoulSelectOutboundPort, TacticCharacterOutboundPort tacticCharacterOutboundPort) {
        this.statisticSoulSelectOutboundPort = statisticSoulSelectOutboundPort;
        this.tacticCharacterOutboundPort = tacticCharacterOutboundPort;
    }

    @Override
    public Long getAddedCount() {
        Long currentCount = tacticCharacterOutboundPort.countAll();
        StatisticSoulselect statisticSoulselect = statisticSoulSelectOutboundPort.getReferenceById(1L);
        Long lastBatchId = statisticSoulselect.getLastBatch();
        if (lastBatchId == null) {
            lastBatchId = 0L;
        }
        return currentCount - lastBatchId;
    }

    @Override
    public void saveLastCount() {
        Long currentCount = tacticCharacterOutboundPort.countAll();
        StatisticSoulselect statisticSoulselect = statisticSoulSelectOutboundPort.getReferenceById(1L);
        statisticSoulselect.setLastBatch(currentCount);
        statisticSoulSelectOutboundPort.save(statisticSoulselect);
    }
}
