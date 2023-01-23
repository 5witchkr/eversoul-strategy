package com.strategy.application.processor;


import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import com.strategy.application.port.inbound.GetRecommendInboundProt;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;
import com.strategy.application.port.inbound.outputdto.SoulCharacterTacticResponseDto;
import com.strategy.application.port.outbound.StageOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RecommendProcessor implements GetRecommendInboundProt {

    private final StageOutboundPort stageOutboundPort;

    public RecommendProcessor(StageOutboundPort stageOutboundPort) {
        this.stageOutboundPort = stageOutboundPort;
    }

    @Override
    public List<RecommendTacticResponseDto> getRecommendWithoutBans(String location, String step, List<String> bans) {
        return Objects.requireNonNull(stageOutboundPort.getByLocationAndStep(location, step).orElse(null))
                .getTactics().stream()
                .map(this::tacticToRecommendTacticResponseDto)
                .filter(value -> !checkBans(value.getSoulCharacterTacticResponseDtos(), bans))
                .collect(Collectors.toList());

    }

    private boolean checkBans(List<SoulCharacterTacticResponseDto> soulCharacterTacticResponseDtos, List<String> bans) {
        return soulCharacterTacticResponseDtos.stream().anyMatch(value -> bans.contains(value.getName()));
    }

    private RecommendTacticResponseDto tacticToRecommendTacticResponseDto(Tactic tactic){
        if (tactic == null) {
            return null;
        }
        RecommendTacticResponseDto.RecommendTacticResponseDtoBuilder
                recommendTacticResponseDto = RecommendTacticResponseDto.builder();
        recommendTacticResponseDto.position(tactic.getPosition());
        recommendTacticResponseDto.power(tactic.getPower());
        recommendTacticResponseDto.info(tactic.getInfo());
        recommendTacticResponseDto.soulCharacterTacticResponseDtos(
                tacticCharactersToResponseDtos(tactic.getTacticCharacters()));
        return recommendTacticResponseDto.build();
    }

    private List<SoulCharacterTacticResponseDto> tacticCharactersToResponseDtos(List<TacticCharacter> tacticCharacters) {
        return tacticCharacters.stream()
                .map(this::tacticCharacterToResponseDto)
                .collect(Collectors.toList());
    }

    private SoulCharacterTacticResponseDto tacticCharacterToResponseDto(TacticCharacter tacticCharacter){
        if (tacticCharacter == null) {
            return null;
        }
        SoulCharacterTacticResponseDto.SoulCharacterTacticResponseDtoBuilder
                soulCharacterTacticResponseDto = SoulCharacterTacticResponseDto.builder();
        soulCharacterTacticResponseDto.name(tacticCharacter.getTacticSoulcharacter().getName());
        soulCharacterTacticResponseDto.level(tacticCharacter.getLevel());
        return soulCharacterTacticResponseDto.build();
    }
}
