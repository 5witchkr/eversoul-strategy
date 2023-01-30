package com.strategy.application.port.inbound.inputdto.souldto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoulCharacterTacticValidReqDto {

    private Long soulId;
    private int level;

    @Builder
    public SoulCharacterTacticValidReqDto(Long soulId, int level){
        this.level = level;
        this.soulId = soulId;
    }
}
