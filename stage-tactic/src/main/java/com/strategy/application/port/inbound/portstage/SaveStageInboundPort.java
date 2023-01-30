package com.strategy.application.port.inbound.portstage;

import com.strategy.application.port.inbound.inputdto.stagedto.StageSaveDto;

public interface SaveStageInboundPort {
    void saveStage(StageSaveDto stageSaveDto);
}
