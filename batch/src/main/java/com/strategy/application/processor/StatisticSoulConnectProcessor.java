package com.strategy.application.processor;


import com.strategy.adapter.outbound.persistence.SoulconnectBatchdata;
import com.strategy.application.port.inbound.StatisticSoulConnectInboundPort;
import com.strategy.application.port.outbound.SoulconnectBatchdataOutboundPort;
import com.strategy.application.port.outbound.TacticCharacterOutboundPort;
import org.springframework.stereotype.Component;

@Component
public class StatisticSoulConnectProcessor implements StatisticSoulConnectInboundPort {

    private final TacticCharacterOutboundPort tacticCharacterOutboundPort;
    private final SoulconnectBatchdataOutboundPort soulconnectBatchdataOutboundPort;

    public StatisticSoulConnectProcessor(TacticCharacterOutboundPort tacticCharacterOutboundPort, SoulconnectBatchdataOutboundPort soulconnectBatchdataOutboundPort) {
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
}