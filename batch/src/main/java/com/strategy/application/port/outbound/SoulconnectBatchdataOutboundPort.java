package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.SoulconnectBatchdata;

public interface SoulconnectBatchdataOutboundPort {

    SoulconnectBatchdata getReferenceById(Long id);

    void save(SoulconnectBatchdata soulconnectBatchdata);
}
