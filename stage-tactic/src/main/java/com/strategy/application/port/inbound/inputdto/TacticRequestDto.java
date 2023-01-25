package com.strategy.application.port.inbound.inputdto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class TacticRequestDto {

    @NotNull
    @Positive
    @Max(100)
    private int location;

    @NotNull
    @Positive
    @Max(300)
    private int step;

    @NotBlank
    @Size(min = 1, max = 30)
    private String position;

    @NotNull
    @Positive
    @Max(1_000_000_000)
    private int power;

    @Size(max = 250)
    private String info;

    @NotEmpty
    @Size(min = 5, max = 5)
    private List<SoulCharacterTacticRequestDto> soulCharacters;

    public TacticRequestDto() {}
}
