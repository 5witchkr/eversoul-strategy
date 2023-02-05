package com.strategy.application.facade;

import com.strategy.application.port.inbound.portbatch.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BatchFacade implements BatchPortFacade{

    private final StatisticSoulSelectInboundPort statisticSoulSelectInboundPort;
    private final SoulSelectBatchInboundPort soulSelectBatchInboundPort;
    private final StatisticPositionInboundPort statisticPositionInboundPort;
    private final PositionBatchInboundPort positionBatchInboundPort;
    private final StatisticSoulConnectDataInboundPort statisticSoulConnectDataInboundPort;
    private final SoulConnectDataBatchInboundPort soulConnectDataBatchInboundPort;
    private final SoulConnectBatchInboundPort soulConnectBatchInboundPort;

    public BatchFacade(StatisticSoulSelectInboundPort statisticSoulSelectInboundPort,
                       SoulSelectBatchInboundPort soulSelectBatchInboundPort,
                       StatisticPositionInboundPort statisticPositionInboundPort,
                       PositionBatchInboundPort positionBatchInboundPort,
                       StatisticSoulConnectDataInboundPort statisticSoulConnectDataInboundPort,
                       SoulConnectDataBatchInboundPort soulConnectDataBatchInboundPort, SoulConnectBatchInboundPort soulConnectBatchInboundPort) {
        this.statisticSoulSelectInboundPort = statisticSoulSelectInboundPort;
        this.soulSelectBatchInboundPort = soulSelectBatchInboundPort;
        this.statisticPositionInboundPort = statisticPositionInboundPort;
        this.positionBatchInboundPort = positionBatchInboundPort;
        this.statisticSoulConnectDataInboundPort = statisticSoulConnectDataInboundPort;
        this.soulConnectDataBatchInboundPort = soulConnectDataBatchInboundPort;
        this.soulConnectBatchInboundPort = soulConnectBatchInboundPort;
    }


    @Override
    public void soulSelectBatch() {
        Long addedCount = statisticSoulSelectInboundPort.getAddedCount();
        soulSelectBatchInboundPort.addData(addedCount);
        statisticSoulSelectInboundPort.saveLastCount();
    }

    @Override
    public void positionBatch() {
        Long addedCount = statisticPositionInboundPort.getAddedCount();
        positionBatchInboundPort.addData(addedCount);
        statisticPositionInboundPort.saveLastCount();
    }

    @Override
    public void soulConnectBatch() {
        Long addedCount = statisticSoulConnectDataInboundPort.getAddedCount();
        soulConnectDataBatchInboundPort.addData(addedCount);
        statisticSoulConnectDataInboundPort.saveLastCount();
        List<Long> tacticIds = statisticSoulConnectDataInboundPort.getAddedTacticId();
        soulConnectBatchInboundPort.addData(tacticIds);
    }
}
