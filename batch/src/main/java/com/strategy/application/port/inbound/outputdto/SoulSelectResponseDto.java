package com.strategy.application.port.inbound.outputdto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoulSelectResponseDto {

    private Long id;
    private int selectCount;

    @Builder
    public SoulSelectResponseDto(Long id, int selectCount){
        this.id = id;
        this.selectCount = selectCount;
    }
}
