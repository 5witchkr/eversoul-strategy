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


    private String title;

    private int power;

    private String info;

    private int recommendCount;
    private List<SoulCharacterTacticResponseDto> soulCharacterTacticResponseDtos;

    public RecommendTacticResponseDto() {}

    @Builder
    public RecommendTacticResponseDto(Long tacticId, String position, String title, int power,String info, List<SoulCharacterTacticResponseDto> soulCharacterTacticResponseDtos, int recommendCount) {
        this.tacticId = tacticId;
        this.title = title;
        this.position = position;
        this.power = power;
        this.info = info;
        this.recommendCount = recommendCount;
        this.soulCharacterTacticResponseDtos = soulCharacterTacticResponseDtos;
    }
}
