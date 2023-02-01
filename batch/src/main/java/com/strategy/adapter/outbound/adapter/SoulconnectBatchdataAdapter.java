package com.strategy.adapter.outbound.adapter;

import com.strategy.adapter.outbound.persistence.SoulconnectBatchdata;
import com.strategy.adapter.outbound.persistence.SoulconnectBatchdataRepository;
import com.strategy.application.port.outbound.SoulconnectBatchdataOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class SoulconnectBatchdataAdapter implements SoulconnectBatchdataOutboundPort {

    private final SoulconnectBatchdataRepository soulconnectBatchdataRepository;

    public SoulconnectBatchdataAdapter(SoulconnectBatchdataRepository soulconnectBatchdataRepository) {
        this.soulconnectBatchdataRepository = soulconnectBatchdataRepository;
    }

    @Override
    public SoulconnectBatchdata getReferenceById(Long id) {
        return soulconnectBatchdataRepository.getReferenceById(id);
    }

    @Override
    public void save(SoulconnectBatchdata soulconnectBatchdata) {
        soulconnectBatchdataRepository.save(soulconnectBatchdata);
    }
}
