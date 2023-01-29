package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.TacticRequestDto;


public interface PostTacticInboundPort {

    void postTactic(TacticRequestDto tacticRequestDtos);
}
