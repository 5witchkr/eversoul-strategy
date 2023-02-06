package com.strategy.adapter.outbound.adapter;

import com.strategy.adapter.outbound.persistence.entity.SoulconnectBatchdata;
import com.strategy.adapter.outbound.persistence.jparepository.SoulconnectBatchdataRepository;
import com.strategy.application.port.outbound.SoulconnectBatchdataOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;


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

    @Override
    public List<SoulconnectBatchdata> findAllByTacticId(Long tacticId) {
        return soulconnectBatchdataRepository.findAllByTacticId(tacticId);
    }

    @Override
    public List<SoulconnectBatchdata> findAllByIsBatched(int isBatched) {
        return soulconnectBatchdataRepository.findAllByIsBatched(isBatched);
    }
}
