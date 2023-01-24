package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.StageSaveDto;

public interface SaveStageInboundPort {
    void saveStage(StageSaveDto stageSaveDto);
}
