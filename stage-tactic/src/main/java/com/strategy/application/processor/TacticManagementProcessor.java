package com.strategy.application.processor;

import com.strategy.application.port.inbound.DeleteTacticInboundPort;
import com.strategy.application.port.inbound.PutTacticInboundPort;
import com.strategy.application.port.inbound.inputdto.TacticPutDto;
import com.strategy.application.port.outbound.TacticOutboundPort;
import org.springframework.stereotype.Component;

@Component
public class TacticManagementProcessor implements DeleteTacticInboundPort, PutTacticInboundPort {
    private final TacticOutboundPort tacticOutboundPort;

    public TacticManagementProcessor(TacticOutboundPort tacticOutboundPort) {
        this.tacticOutboundPort = tacticOutboundPort;
    }

    @Override
    public void deleteTactic(Long id) {
        tacticOutboundPort.deleteById(id);
    }

    @Override
    public void putTactic(TacticPutDto tacticPutDto) {

    }
}
