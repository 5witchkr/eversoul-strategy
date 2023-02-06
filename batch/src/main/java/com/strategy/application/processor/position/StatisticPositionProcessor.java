package com.strategy.application.processor.position;


import com.strategy.adapter.outbound.persistence.entity.StatisticPosition;
import com.strategy.application.port.inbound.portbatch.StatisticPositionInboundPort;
import com.strategy.application.port.outbound.StatisticPositionOutboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import org.springframework.stereotype.Component;

@Component
public class StatisticPositionProcessor implements StatisticPositionInboundPort {

    private final TacticOutboundPort tacticOutboundPort;
    private final StatisticPositionOutboundPort statisticPositionOutboundPort;

    public StatisticPositionProcessor(TacticOutboundPort tacticOutboundPort, StatisticPositionOutboundPort statisticPositionOutboundPort) {
        this.tacticOutboundPort = tacticOutboundPort;
        this.statisticPositionOutboundPort = statisticPositionOutboundPort;
    }


    @Override
    public Long getAddedCount() {
        Long currentCount = tacticOutboundPort.countAll();
        StatisticPosition statisticPosition = statisticPositionOutboundPort.getReferenceById(1L);
        Long lastBatchId = statisticPosition.getLastBatch();
        if (lastBatchId == null) {
            lastBatchId = 0L;
        }
        return currentCount - lastBatchId;
    }

    @Override
    public void saveLastCount() {
        Long currentCount = tacticOutboundPort.countAll();
        StatisticPosition statisticPosition = statisticPositionOutboundPort.getReferenceById(1L);
        statisticPosition.setLastBatch(currentCount);
        statisticPositionOutboundPort.save(statisticPosition);
    }
}
