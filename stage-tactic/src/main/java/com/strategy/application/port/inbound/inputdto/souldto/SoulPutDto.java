package com.strategy.application.port.inbound.inputdto.souldto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SoulPutDto {
    private Long id;

    private String name;

    private String type;

    private String tier;
}
