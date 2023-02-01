package com.strategy.application.facade;

import com.strategy.application.port.inbound.*;
import org.springframework.stereotype.Service;


@Service
public class BatchFacade {

    private final StatisticSoulSelectInboundPort statisticSoulSelectInboundPort;
    private final SoulSelectBatchInboundPort soulSelectBatchInboundPort;
    private final StatisticPositionInboundPort statisticPositionInboundPort;
    private final PositionBatchInboundPort positionBatchInboundPort;
    private final StatisticSoulConnectInboundPort statisticSoulConnectInboundPort;
    private final SoulConnectBatchInboundPort soulConnectBatchInboundPort;

    public BatchFacade(StatisticSoulSelectInboundPort statisticSoulSelectInboundPort, SoulSelectBatchInboundPort soulSelectBatchInboundPort, StatisticPositionInboundPort statisticPositionInboundPort, PositionBatchInboundPort positionBatchInboundPort, StatisticSoulConnectInboundPort statisticSoulConnectInboundPort, SoulConnectBatchInboundPort soulConnectBatchInboundPort) {
        this.statisticSoulSelectInboundPort = statisticSoulSelectInboundPort;
        this.soulSelectBatchInboundPort = soulSelectBatchInboundPort;
        this.statisticPositionInboundPort = statisticPositionInboundPort;
        this.positionBatchInboundPort = positionBatchInboundPort;
        this.statisticSoulConnectInboundPort = statisticSoulConnectInboundPort;
        this.soulConnectBatchInboundPort = soulConnectBatchInboundPort;
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

    public void soulConnectBatch() {
        Long addedCount = statisticSoulConnectInboundPort.getAddedCount();
        soulConnectBatchInboundPort.addData(addedCount);
        statisticSoulConnectInboundPort.saveLastCount();
    }
}
