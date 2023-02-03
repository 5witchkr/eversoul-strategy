package com.strategy.application.port.inbound.inputdto.tacticdto;


import com.strategy.application.port.inbound.inputdto.souldto.SoulCharacterTacticValidReqDto;
import lombok.Builder;
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
    @Size(min = 1, max = 25)
    private String title;

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
    private List<SoulCharacterTacticValidReqDto> soulCharacters;

    @Builder
    public TacticRequestDto(int location, int step, String title,
                            String position, int power, String info,
                            List<SoulCharacterTacticValidReqDto> soulCharacters) {
        this.location = location;
        this.step = step;
        this.title = title;
        this.position = position;
        this.power = power;
        this.info = info;
        this.soulCharacters = soulCharacters;
    }
}
