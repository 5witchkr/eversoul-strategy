package com.strategy.application.port.inbound.portstage;

import com.strategy.application.port.inbound.inputdto.stagedto.StagePutDto;

public interface PutStageInboundPort {
    void putStage(StagePutDto stagePutDto);
}
