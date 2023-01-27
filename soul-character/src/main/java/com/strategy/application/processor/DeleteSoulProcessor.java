package com.strategy.application.processor;

import com.strategy.application.port.inbound.DeleteSoulInboundPort;
import com.strategy.application.port.outbound.SoulManagementOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class DeleteSoulProcessor implements DeleteSoulInboundPort {

    private final SoulManagementOutboundPort soulManagementOutboundPort;

    public DeleteSoulProcessor(SoulManagementOutboundPort soulManagementOutboundPort) {
        this.soulManagementOutboundPort = soulManagementOutboundPort;
    }

    @Override
    public void deleteSoul(Long id) {
        soulManagementOutboundPort.deleteById(id);
    }
}
