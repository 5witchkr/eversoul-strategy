package com.strategy.application.port.inbound.inputdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoulPutRequestDto {
    private Long id;

    private String name;

    private String type;

    private String tier;
}
