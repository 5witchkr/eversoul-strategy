package com.strategy.application.port.inbound.porttactic;

import com.strategy.application.port.inbound.inputdto.tacticdto.TacticRequestDto;


public interface PostTacticInboundPort {

    void postTactic(TacticRequestDto tacticRequestDtos);
}
