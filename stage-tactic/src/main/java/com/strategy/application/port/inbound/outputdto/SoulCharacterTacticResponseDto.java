package com.strategy.application.port.inbound.outputdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoulCharacterTacticResponseDto {
    private String name;
    private int level;

    public SoulCharacterTacticResponseDto() {}

    @Builder
    public SoulCharacterTacticResponseDto(String name, int level) {
        this.name = name;
        this.level = level;
    }
}
