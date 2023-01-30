package com.strategy.application.port.inbound.porttactic;

import com.strategy.application.port.inbound.inputdto.tacticdto.TacticPutDto;

public interface PutTacticInboundPort {

    void putTactic(TacticPutDto tacticPutDto);
}
