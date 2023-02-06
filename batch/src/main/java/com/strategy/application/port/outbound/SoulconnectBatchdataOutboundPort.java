package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.entity.SoulconnectBatchdata;

import java.util.List;

public interface SoulconnectBatchdataOutboundPort {

    SoulconnectBatchdata getReferenceById(Long id);

    void save(SoulconnectBatchdata soulconnectBatchdata);

    List<SoulconnectBatchdata> findAllByTacticId(Long tacticId);

    List<SoulconnectBatchdata> findAllByIsBatched(int isBatched);
}
