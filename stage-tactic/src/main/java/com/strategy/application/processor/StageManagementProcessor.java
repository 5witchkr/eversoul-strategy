package com.strategy.application.processor;

import com.strategy.adpater.outbound.persistence.entity.Stage;
import com.strategy.application.port.inbound.DeleteStageInboundPort;
import com.strategy.application.port.inbound.PutStageInboundPort;
import com.strategy.application.port.inbound.SaveStageInboundPort;
import com.strategy.application.port.inbound.inputdto.StagePutDto;
import com.strategy.application.port.inbound.inputdto.StageSaveDto;
import com.strategy.application.port.outbound.StageOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class StageManagementProcessor implements SaveStageInboundPort, PutStageInboundPort, DeleteStageInboundPort {

    private final StageOutboundPort stageOutboundPort;

    public StageManagementProcessor(StageOutboundPort stageOutboundPort) {
        this.stageOutboundPort = stageOutboundPort;
    }

    @Override
    public void deleteStage(Long id) {
        stageOutboundPort.deleteById(id);
    }

    @Override
    public void putStage(StagePutDto stagePutDto) {
        checkExistsStage(stagePutDto.getLocation(), stagePutDto.getStep());
        Stage stage = stageOutboundPort.findById(stagePutDto.getId())
                .orElseThrow(() -> new NullPointerException("유효하지 않은 정보"));
        stage.setLocation(stagePutDto.getLocation());
        stage.setStep(stagePutDto.getStep());
        stage.setPosition(stagePutDto.getPosition());
        stage.setPower(stagePutDto.getPower());
        stage.setSoulCharacters(String.join(",",stagePutDto.getSoulCharacters()));
        stageOutboundPort.save(stage);
    }

    @Override
    public void saveStage(StageSaveDto stageSaveDto) {
        checkExistsStage(stageSaveDto.getLocation(), stageSaveDto.getStep());
        Stage stage = Stage.builder()
                .location(stageSaveDto.getLocation())
                .step(stageSaveDto.getStep())
                .position(stageSaveDto.getPosition())
                .power(stageSaveDto.getPower())
                .soulCharacters(String.join(",",stageSaveDto.getSoulCharacters()))
                .build();
        stageOutboundPort.save(stage);
    }

    private void checkExistsStage(int location, int step) {
        if (stageOutboundPort.existsByLocationAndStep(location, step)){
            throw new RuntimeException("이미 존재하는 스테이지");
        };
    }
}
