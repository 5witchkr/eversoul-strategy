package com.strategy.application.facade;


import com.strategy.application.port.inbound.*;
import com.strategy.application.port.inbound.inputdto.*;
import com.strategy.application.validator.SoulNameValidator;
import org.springframework.stereotype.Service;

@Service
public class StageTacticManagementFacade {

    private final SaveTacticSoulInboundPort saveTacticSoulInboundPort;
    private final PutTacticSoulInboundPort putTacticSoulInboundPort;
    private final DeleteTacticSoulInboundPort deleteTacticSoulInboundPort;
    private final DeleteTacticInboundPort deleteTacticInboundPort;
    private final PutTacticInboundPort putTacticInboundPort;
    private final SaveStageInboundPort saveStageInboundPort;
    private final PutStageInboundPort putStageInboundPort;
    private final DeleteStageInboundPort deleteStageInboundPort;
    private final SoulNameValidator soulNameValidator;

    public StageTacticManagementFacade(SaveTacticSoulInboundPort saveTacticSoulInboundPort,
                                       PutTacticSoulInboundPort putTacticSoulInboundPort,
                                       DeleteTacticSoulInboundPort deleteTacticSoulInboundPort,
                                       DeleteTacticInboundPort deleteTacticInboundPort,
                                       PutTacticInboundPort putTacticInboundPort,
                                       SaveStageInboundPort saveStageInboundPort,
                                       PutStageInboundPort putStageInboundPort,
                                       DeleteStageInboundPort deleteStageInboundPort,
                                       SoulNameValidator soulNameValidator) {
        this.saveTacticSoulInboundPort = saveTacticSoulInboundPort;
        this.putTacticSoulInboundPort = putTacticSoulInboundPort;
        this.deleteTacticSoulInboundPort = deleteTacticSoulInboundPort;
        this.deleteTacticInboundPort = deleteTacticInboundPort;
        this.putTacticInboundPort = putTacticInboundPort;
        this.saveStageInboundPort = saveStageInboundPort;
        this.putStageInboundPort = putStageInboundPort;
        this.deleteStageInboundPort = deleteStageInboundPort;
        this.soulNameValidator = soulNameValidator;
    }


    public void saveSoul(SoulSaveDto soulSaveDto) {
        soulNameValidator.checkSoulName(soulSaveDto.getName());
        saveTacticSoulInboundPort.saveSoul(soulSaveDto);
    }

    public void saveStage(StageSaveDto stageSaveDto) {
        saveStageInboundPort.saveStage(stageSaveDto);
    }

    public void deleteTactic(Long id) {
        deleteTacticInboundPort.deleteTactic(id);
    }

    public void deleteStage(Long id) {
        deleteStageInboundPort.deleteStage(id);
    }

    public void deleteSoul(Long id) {
        deleteTacticSoulInboundPort.deleteSoul(id);
    }

    public void putTactic(TacticPutDto tacticPutDto) {
        putTacticInboundPort.putTactic(tacticPutDto);
    }

    public void putStage(StagePutDto stagePutDto) {
        putStageInboundPort.putStage(stagePutDto);
    }

    public void putSoul(SoulPutDto soulPutDto) {
        soulNameValidator.checkSoulName(soulPutDto.getName());
        putTacticSoulInboundPort.putSoul(soulPutDto);
    }
}
