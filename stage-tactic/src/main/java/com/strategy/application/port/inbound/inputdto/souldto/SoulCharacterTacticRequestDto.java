package com.strategy.application.port.inbound.inputdto.souldto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SoulCharacterTacticRequestDto {
    private String name;
    private int level;

    @Builder
    public SoulCharacterTacticRequestDto(String name, int level){
        this.level = level;
        this.name = name;
    }
}
