package com.strategy.application.processor;

import com.strategy.adpater.outbound.persistence.entity.Stage;
import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.application.port.inbound.PostTacticInboundPort;
import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticRequestDto;
import com.strategy.application.port.inbound.inputdto.TacticRequestDto;
import com.strategy.application.port.outbound.StageOutboundPort;
import com.strategy.application.port.outbound.TacticCharacterOutboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import com.strategy.application.port.outbound.TacticSoulcharacterOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TacticProcessor implements PostTacticInboundPort {

    private final TacticOutboundPort tacticOutboundPort;
    private final StageOutboundPort stageOutboundPort;
    private final TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort;
    private final TacticCharacterOutboundPort tacticCharacterOutboundPort;

    public TacticProcessor(TacticOutboundPort tacticOutboundPort, StageOutboundPort stageOutboundPort, TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort, TacticCharacterOutboundPort tacticCharacterOutboundPort) {
        this.tacticOutboundPort = tacticOutboundPort;
        this.stageOutboundPort = stageOutboundPort;
        this.tacticSoulcharacterOutboundPort = tacticSoulcharacterOutboundPort;
        this.tacticCharacterOutboundPort = tacticCharacterOutboundPort;
    }

    @Override
    public void postTactic(TacticRequestDto tacticRequestDto) {
        Tactic tactic = tacticOutboundPort.save(Tactic.builder()
                .stage(getByLocationAndStep(tacticRequestDto.getLocation(), tacticRequestDto.getStep()))
                .info(tacticRequestDto.getInfo())
                .position(tacticRequestDto.getPosition())
                .power(tacticRequestDto.getPower())
                .build());

        inputTacticCharacter(tacticRequestDto.getSoulCharacters(), tactic);
    }

    private void inputTacticCharacter(List<SoulCharacterTacticRequestDto> soulCharacterTacticRequestDtos, Tactic tactic) {
        soulCharacterTacticRequestDtos.forEach(value -> tacticCharacterOutboundPort.save(TacticCharacter.builder()
                .tactic(tactic)
                .level(value.getLevel())
                .tacticSoulcharacter(getTacticSoulcharacter(value))
                .build()));
    }

    private TacticSoulcharacter getTacticSoulcharacter(SoulCharacterTacticRequestDto value) {
        return tacticSoulcharacterOutboundPort.getByName(value.getName())
                .orElseThrow(() -> new RuntimeException("유효하지 않은 정령"));
    }

    private Stage getByLocationAndStep(String location, String step) {
        return stageOutboundPort.getByLocationAndStep(location, step)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 스테이지"));
    }
}
