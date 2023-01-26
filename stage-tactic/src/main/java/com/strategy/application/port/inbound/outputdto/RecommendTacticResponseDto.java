package com.strategy.application.port.inbound.outputdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecommendTacticResponseDto {

    private Long tacticId;
    private String position;
    private int power;

    private String info;
    private List<SoulCharacterTacticResponseDto> soulCharacterTacticResponseDtos;

    public RecommendTacticResponseDto() {}

    @Builder
    public RecommendTacticResponseDto(Long tacticId, String position, int power,String info, List<SoulCharacterTacticResponseDto> soulCharacterTacticResponseDtos) {
        this.tacticId = tacticId;
        this.position = position;
        this.power = power;
        this.info = info;
        this.soulCharacterTacticResponseDtos = soulCharacterTacticResponseDtos;
    }
}
