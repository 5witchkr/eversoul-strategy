package com.strategy.application.port.inbound.inputdto.stagedto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StageSaveDto {

    private int location;

    private int step;

    private String position;

    private int power;

    private List<String> soulCharacters;
}
