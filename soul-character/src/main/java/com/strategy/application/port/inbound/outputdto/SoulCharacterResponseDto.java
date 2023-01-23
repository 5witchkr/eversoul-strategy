package com.strategy.application.port.inbound.outputdto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoulCharacterResponseDto {
    private Long id;

    private String name;

    private String type;

    private String tier;

    public SoulCharacterResponseDto(){}

    @Builder
    public SoulCharacterResponseDto(Long id, String name, String type, String tier) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.tier = tier;
    }
}
