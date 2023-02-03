package com.strategy.application.facade;

import com.strategy.application.port.inbound.inputdto.souldto.SoulPutDto;
import com.strategy.application.port.inbound.inputdto.souldto.SoulSaveDto;
import com.strategy.application.port.inbound.inputdto.stagedto.StagePutDto;
import com.strategy.application.port.inbound.inputdto.stagedto.StageSaveDto;
import com.strategy.application.port.inbound.inputdto.tacticdto.TacticPutDto;

public interface StageTacticManagementPortFacade {

    void saveSoul(SoulSaveDto soulSaveDto);

    void saveStage(StageSaveDto stageSaveDto);

    void deleteTactic(Long id);

    void deleteStage(Long id);

    void deleteSoul(Long id);

    void putTactic(TacticPutDto tacticPutDto);

    void putStage(StagePutDto stagePutDto);

    void putSoul(SoulPutDto soulPutDto);
}
