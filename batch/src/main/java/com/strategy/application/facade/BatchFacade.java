package com.strategy.application.facade;

import com.strategy.application.port.inbound.PositionBatchInboundPort;
import com.strategy.application.port.inbound.SoulSelectBatchInboundPort;
import com.strategy.application.port.inbound.StatisticPositionInboundPort;
import com.strategy.application.port.inbound.StatisticSoulSelectInboundPort;
import org.springframework.stereotype.Service;


@Service
public class BatchFacade {

    private final StatisticSoulSelectInboundPort statisticSoulSelectInboundPort;
    private final SoulSelectBatchInboundPort soulSelectBatchInboundPort;
    private final StatisticPositionInboundPort statisticPositionInboundPort;
    private final PositionBatchInboundPort positionBatchInboundPort;

    public BatchFacade(StatisticSoulSelectInboundPort statisticSoulSelectInboundPort, SoulSelectBatchInboundPort soulSelectBatchInboundPort, StatisticPositionInboundPort statisticPositionInboundPort, PositionBatchInboundPort positionBatchInboundPort) {
        this.statisticSoulSelectInboundPort = statisticSoulSelectInboundPort;
        this.soulSelectBatchInboundPort = soulSelectBatchInboundPort;
        this.statisticPositionInboundPort = statisticPositionInboundPort;
        this.positionBatchInboundPort = positionBatchInboundPort;
    }



    public void soulSelectBatch() {
        Long addedCount = statisticSoulSelectInboundPort.getAddedCount();
        soulSelectBatchInboundPort.addData(addedCount);
        statisticSoulSelectInboundPort.saveLastCount();
    }

    public void positionBatch() {
        Long addedCount = statisticPositionInboundPort.getAddedCount();
        positionBatchInboundPort.addData(addedCount);
        statisticPositionInboundPort.saveLastCount();
    }
}
