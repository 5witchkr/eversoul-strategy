package com.strategy.application.port.inbound.inputdto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TacticRequestDto {
    private int location;
    private int step;
    private String position;
    private int power;
    private String info;
    private List<SoulCharacterTacticRequestDto> soulCharacters;

    public TacticRequestDto() {}
}
