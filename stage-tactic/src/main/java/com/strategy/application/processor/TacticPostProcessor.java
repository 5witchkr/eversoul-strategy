package com.strategy.application.processor;

import com.strategy.adpater.outbound.persistence.entity.Stage;
import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.application.port.inbound.PostTacticInboundPort;
import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticValidReqDto;
import com.strategy.application.port.inbound.inputdto.TacticRequestDto;
import com.strategy.application.port.outbound.StageOutboundPort;
import com.strategy.application.port.outbound.TacticCharacterOutboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import com.strategy.application.port.outbound.TacticSoulcharacterOutboundPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TacticPostProcessor implements PostTacticInboundPort {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final TacticOutboundPort tacticOutboundPort;
    private final StageOutboundPort stageOutboundPort;
    private final TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort;
    private final TacticCharacterOutboundPort tacticCharacterOutboundPort;


    public TacticPostProcessor(TacticOutboundPort tacticOutboundPort, StageOutboundPort stageOutboundPort, TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort, TacticCharacterOutboundPort tacticCharacterOutboundPort) {
        this.tacticOutboundPort = tacticOutboundPort;
        this.stageOutboundPort = stageOutboundPort;
        this.tacticSoulcharacterOutboundPort = tacticSoulcharacterOutboundPort;
        this.tacticCharacterOutboundPort = tacticCharacterOutboundPort;
    }

    @Override
    @Transactional
    public void postTactic(TacticRequestDto tacticRequestDto) {
        Tactic tactic = tacticOutboundPort.save(Tactic.builder()
                .stage(getByLocationAndStep(tacticRequestDto.getLocation(), tacticRequestDto.getStep()))
                .info(tacticRequestDto.getInfo())
                .title(tacticRequestDto.getTitle())
                .position(tacticRequestDto.getPosition())
                .power(tacticRequestDto.getPower())
                .build());

        inputTacticCharacter(tacticRequestDto.getSoulCharacters(), tactic);
    }


    private void inputTacticCharacter(List<SoulCharacterTacticValidReqDto> soulCharacterTacticValidReqDtos, Tactic tactic) {
        List<TacticCharacter> tacticCharacters =
                soulCharacterTacticValidReqDtos.stream()
                        .map(value -> buildTacticCharacter(tactic,value))
                        .collect(Collectors.toList());
        try {
            tacticCharacterOutboundPort.saveAll(tacticCharacters);
        } catch (RuntimeException runtimeException){
            log.warn("fail saveAll tacticCharacters");
            throw new IllegalArgumentException("유효하지 않은 정보");
        }
    }


    private TacticCharacter buildTacticCharacter(Tactic tactic, SoulCharacterTacticValidReqDto value) {
        return TacticCharacter.builder()
                .tactic(tactic)
                .level(value.getLevel())
                .tacticSoulcharacter(getTacticSoulcharacter(value.getSoulId()))
                .build();
    }


    private TacticSoulcharacter getTacticSoulcharacter(Long id) {
        return tacticSoulcharacterOutboundPort.getByReferenceId(id);
    }

    private Stage getByLocationAndStep(int location, int step) {
        return stageOutboundPort.getByLocationAndStep(location, step)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 스테이지"));
    }
}
