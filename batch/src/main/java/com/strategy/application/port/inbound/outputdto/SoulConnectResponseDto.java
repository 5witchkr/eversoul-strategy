package com.strategy.application.port.inbound.outputdto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoulConnectResponseDto {

    private Long connectSoul;

    private Long connectedSoul;

    private int connectCount;

    @Builder
    public SoulConnectResponseDto(Long connectSoul, Long connectedSoul, int connectCount){
        this.connectSoul = connectSoul;
        this.connectedSoul = connectedSoul;
        this. connectCount = connectCount;
    }
}
