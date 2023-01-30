package com.strategy.application.processor;


import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import com.strategy.application.port.inbound.porttactic.GetTacticInboundPort;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;
import com.strategy.application.port.inbound.outputdto.SoulCharacterTacticResponseDto;
import com.strategy.application.port.outbound.StageOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GetTacticProcessor implements GetTacticInboundPort {

    private final StageOutboundPort stageOutboundPort;

    public GetTacticProcessor(StageOutboundPort stageOutboundPort) {
        this.stageOutboundPort = stageOutboundPort;
    }

    private static final CheckBans<List<SoulCharacterTacticResponseDto>, List<String>> checkBans =
            (dto, ban) -> dto.stream().anyMatch(target -> ban.contains(target.getName()));

    @Override
    public List<RecommendTacticResponseDto> getRecommendWithoutBans(int location, int step, List<String> bans) {
        return Objects.requireNonNull(stageOutboundPort.getByLocationAndStep(location, step).orElse(null))
                .getTactics().stream()
                .map(this::tacticToRecommendTacticResponseDto)
                .filter(value -> !checkBans.check(value.getSoulCharacterTacticResponseDtos(),bans))
                .sorted(Comparator.comparingInt(RecommendTacticResponseDto::getRecommendCount).reversed())
                .collect(Collectors.toList());
    }

    private RecommendTacticResponseDto tacticToRecommendTacticResponseDto(Tactic tactic){
        if (tactic == null) {
            return null;
        }
        RecommendTacticResponseDto.RecommendTacticResponseDtoBuilder
                recommendTacticResponseDto = RecommendTacticResponseDto.builder();
        recommendTacticResponseDto.tacticId(tactic.getId());
        recommendTacticResponseDto.position(tactic.getPosition());
        recommendTacticResponseDto.title(tactic.getTitle());
        recommendTacticResponseDto.power(tactic.getPower());
        recommendTacticResponseDto.info(tactic.getInfo());
        recommendTacticResponseDto.recommendCount(tactic.getRecommendCount());
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
        soulCharacterTacticResponseDto.id(tacticCharacter.getTacticSoulcharacter().getId());
        soulCharacterTacticResponseDto.name(tacticCharacter.getTacticSoulcharacter().getName());
        soulCharacterTacticResponseDto.level(tacticCharacter.getLevel());
        return soulCharacterTacticResponseDto.build();
    }
}
