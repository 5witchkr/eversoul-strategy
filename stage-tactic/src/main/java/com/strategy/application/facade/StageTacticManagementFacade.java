package com.strategy.application.facade;


import com.strategy.application.port.inbound.*;
import com.strategy.application.port.inbound.inputdto.*;
import com.strategy.application.validator.SoulNameValidator;
import org.springframework.stereotype.Service;

@Service
public class StageTacticManagementFacade {

    private final SaveSoulInboundPort saveSoulInboundPort;
    private final PutSoulInboundPort putSoulInboundPort;
    private final DeleteSoulInboundPort deleteSoulInboundPort;
    private final DeleteTacticInboundPort deleteTacticInboundPort;
    private final PutTacticInboundPort putTacticInboundPort;
    private final SaveStageInboundPort saveStageInboundPort;
    private final PutStageInboundPort putStageInboundPort;
    private final DeleteStageInboundPort deleteStageInboundPort;
    private final SoulNameValidator soulNameValidator;

    public StageTacticManagementFacade(SaveSoulInboundPort saveSoulInboundPort,
                                       PutSoulInboundPort putSoulInboundPort,
                                       DeleteSoulInboundPort deleteSoulInboundPort,
                                       DeleteTacticInboundPort deleteTacticInboundPort,
                                       PutTacticInboundPort putTacticInboundPort,
                                       SaveStageInboundPort saveStageInboundPort,
                                       PutStageInboundPort putStageInboundPort,
                                       DeleteStageInboundPort deleteStageInboundPort,
                                       SoulNameValidator soulNameValidator) {
        this.saveSoulInboundPort = saveSoulInboundPort;
        this.putSoulInboundPort = putSoulInboundPort;
        this.deleteSoulInboundPort = deleteSoulInboundPort;
        this.deleteTacticInboundPort = deleteTacticInboundPort;
        this.putTacticInboundPort = putTacticInboundPort;
        this.saveStageInboundPort = saveStageInboundPort;
        this.putStageInboundPort = putStageInboundPort;
        this.deleteStageInboundPort = deleteStageInboundPort;
        this.soulNameValidator = soulNameValidator;
    }


    public void saveSoul(SoulSaveDto soulSaveDto) {
        soulNameValidator.checkSoulName(soulSaveDto.getName());
        saveSoulInboundPort.saveSoul(soulSaveDto);
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
        deleteSoulInboundPort.deleteSoul(id);
    }

    public void putTactic(TacticPutDto tacticPutDto) {
        putTacticInboundPort.putTactic(tacticPutDto);
    }

    public void putStage(StagePutDto stagePutDto) {
        putStageInboundPort.putStage(stagePutDto);
    }

    public void putSoul(SoulPutDto soulPutDto) {
        soulNameValidator.checkSoulName(soulPutDto.getName());
        putSoulInboundPort.putSoul(soulPutDto);
    }
}
