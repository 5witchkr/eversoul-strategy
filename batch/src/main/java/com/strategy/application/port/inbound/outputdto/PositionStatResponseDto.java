package com.strategy.application.port.inbound.outputdto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionStatResponseDto {

    private String positionName;

    private int positionCount;

    @Builder
    public PositionStatResponseDto (String positionName, int positionCount){
        this.positionName = positionName;
        this.positionCount = positionCount;
    }
}
