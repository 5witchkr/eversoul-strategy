package com.strategy.application.port.inbound.outputdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoulCharacterTacticResponseDto {
    private String name;
    private String level;

    public SoulCharacterTacticResponseDto() {}

    @Builder
    public SoulCharacterTacticResponseDto(String name, String level) {
        this.name = name;
        this.level = level;
    }
}
