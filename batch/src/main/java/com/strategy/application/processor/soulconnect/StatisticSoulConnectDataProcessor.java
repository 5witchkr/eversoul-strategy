package com.strategy.application.processor.soulconnect;


import com.strategy.adapter.outbound.persistence.entity.SoulconnectBatchdata;
import com.strategy.application.port.inbound.portbatch.StatisticSoulConnectDataInboundPort;
import com.strategy.application.port.outbound.SoulconnectBatchdataOutboundPort;
import com.strategy.application.port.outbound.TacticCharacterOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatisticSoulConnectDataProcessor implements StatisticSoulConnectDataInboundPort {

    private final TacticCharacterOutboundPort tacticCharacterOutboundPort;
    private final SoulconnectBatchdataOutboundPort soulconnectBatchdataOutboundPort;

    public StatisticSoulConnectDataProcessor(TacticCharacterOutboundPort tacticCharacterOutboundPort, SoulconnectBatchdataOutboundPort soulconnectBatchdataOutboundPort) {
        this.tacticCharacterOutboundPort = tacticCharacterOutboundPort;
        this.soulconnectBatchdataOutboundPort = soulconnectBatchdataOutboundPort;
    }

    @Override
    public Long getAddedCount() {
        Long currentCount = tacticCharacterOutboundPort.countAll();
        SoulconnectBatchdata soulconnectBatchdata = soulconnectBatchdataOutboundPort.getReferenceById(1L);
        Long lastBatchId = soulconnectBatchdata.getLastBatch();
        if (lastBatchId == null) {
            lastBatchId = 0L;
        }
        return currentCount - lastBatchId;
    }

    @Override
    public void saveLastCount() {
        Long currentCount = tacticCharacterOutboundPort.countAll();
        SoulconnectBatchdata soulconnectBatchdata = soulconnectBatchdataOutboundPort.getReferenceById(1L);
        soulconnectBatchdata.setLastBatch(currentCount);
        soulconnectBatchdataOutboundPort.save(soulconnectBatchdata);
    }

    @Override
    public List<Long> getAddedTacticId() {
        return soulconnectBatchdataOutboundPort.findAllByIsBatched(0)
                .stream()
                .map(SoulconnectBatchdata::isBatchDone)
                .map(SoulconnectBatchdata::getTacticId)
                .distinct()
                .collect(Collectors.toList());
    }
}