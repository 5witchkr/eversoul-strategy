package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.StagePutDto;

public interface PutStageInboundPort {
    void putStage(StagePutDto stagePutDto);
}
