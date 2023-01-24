package com.strategy.application.facade;


import com.strategy.application.port.inbound.inputdto.*;
import com.strategy.application.processor.SoulManagementProcessor;
import com.strategy.application.processor.StageManagementProcessor;
import com.strategy.application.processor.TacticManagementProcessor;
import org.springframework.stereotype.Service;

@Service
public class StageTacticManagementFacade {

    private final SoulManagementProcessor soulManagementProcessor;
    private final TacticManagementProcessor tacticManagementProcessor;
    private final StageManagementProcessor stageManagementProcessor;

    public StageTacticManagementFacade(SoulManagementProcessor soulManagementProcessor, TacticManagementProcessor tacticManagementProcessor, StageManagementProcessor stageManagementProcessor) {
        this.soulManagementProcessor = soulManagementProcessor;
        this.tacticManagementProcessor = tacticManagementProcessor;
        this.stageManagementProcessor = stageManagementProcessor;
    }


    public void saveSoul(SoulSaveDto soulSaveDto) {
        soulManagementProcessor.saveSoul(soulSaveDto);
    }

    public void saveStage(StageSaveDto stageSaveDto) {
        stageManagementProcessor.saveStage(stageSaveDto);
    }

    public void deleteTactic(Long id) {
        tacticManagementProcessor.deleteTactic(id);
    }

    public void deleteStage(Long id) {
        stageManagementProcessor.deleteStage(id);
    }

    public void deleteSoul(Long id) {
        soulManagementProcessor.deleteSoul(id);
    }

    public void putTactic(TacticPutDto tacticPutDto) {
        tacticManagementProcessor.putTactic(tacticPutDto);
    }

    public void putStage(StagePutDto stagePutDto) {
        stageManagementProcessor.putStage(stagePutDto);
    }

    public void putSoul(SoulPutDto soulPutDto) {
        soulManagementProcessor.putSoul(soulPutDto);
    }
}
