package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.TacticPutDto;

public interface PutTacticInboundPort {

    void putTactic(TacticPutDto tacticPutDto);
}
