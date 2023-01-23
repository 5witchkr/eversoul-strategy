package com.strategy.application.port.inbound.outputdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecommendTacticResponseDto {
    private String position;
    private String power;

    private String info;
    private List<SoulCharacterTacticResponseDto> soulCharacterTacticResponseDtos;

    public RecommendTacticResponseDto() {}

    @Builder
    public RecommendTacticResponseDto(String position, String power,String info, List<SoulCharacterTacticResponseDto> soulCharacterTacticResponseDtos) {
        this.position = position;
        this.power = power;
        this.info = info;
        this.soulCharacterTacticResponseDtos = soulCharacterTacticResponseDtos;
    }
}
