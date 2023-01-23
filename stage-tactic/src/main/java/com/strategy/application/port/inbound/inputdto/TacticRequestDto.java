package com.strategy.application.port.inbound.inputdto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TacticRequestDto {
    private String location;
    private String step;
    private String position;
    private String power;
    private String info;
    private List<SoulCharacterTacticRequestDto> soulCharacters;

    public TacticRequestDto() {}
}
