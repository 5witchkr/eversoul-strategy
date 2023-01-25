package com.strategy.application.port.inbound.outputdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoulCharacterTacticResponseDto {
    private Long id;
    private String name;
    private int level;

    public SoulCharacterTacticResponseDto() {}

    @Builder
    public SoulCharacterTacticResponseDto(Long id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }
}
