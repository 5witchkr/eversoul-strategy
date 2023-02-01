package com.strategy.application.facade;

import com.strategy.application.port.inbound.SoulSelectBatchInboundPort;
import com.strategy.application.port.inbound.StatisticSoulSelectInboundPort;
import org.springframework.stereotype.Service;


@Service
public class BatchFacade {

    private final StatisticSoulSelectInboundPort statisticSoulSelectInboundPort;
    private final SoulSelectBatchInboundPort soulSelectBatchInboundPort;

    public BatchFacade(StatisticSoulSelectInboundPort statisticSoulSelectInboundPort, SoulSelectBatchInboundPort soulSelectBatchInboundPort) {
        this.statisticSoulSelectInboundPort = statisticSoulSelectInboundPort;
        this.soulSelectBatchInboundPort = soulSelectBatchInboundPort;
    }



    public void soulSelectBatch() {
        Long addedCount = statisticSoulSelectInboundPort.getAddedCount();
        soulSelectBatchInboundPort.addData(addedCount);
        statisticSoulSelectInboundPort.saveLastCount();
    }

    public void positionBatch() {

    }
}
