package com.strategy.application.facade;

import com.strategy.application.port.inbound.DeleteSoulInboundPort;
import com.strategy.application.port.inbound.PutSoulInboundPort;
import com.strategy.application.port.inbound.SaveSoulInboundPort;
import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;
import org.springframework.stereotype.Service;

@Service
public class SoulManagementFacade {

    private final SaveSoulInboundPort saveSoulInboundPort;
    private final PutSoulInboundPort putSoulInboundPort;
    private final DeleteSoulInboundPort deleteSoulInboundPort;

    public SoulManagementFacade(SaveSoulInboundPort saveSoulInboundPort, PutSoulInboundPort putSoulInboundPort, DeleteSoulInboundPort deleteSoulInboundPort) {
        this.saveSoulInboundPort = saveSoulInboundPort;
        this.putSoulInboundPort = putSoulInboundPort;
        this.deleteSoulInboundPort = deleteSoulInboundPort;
    }


    public void saveSoul(SoulSaveRequestDto soulSaveRequestDto) {
        saveSoulInboundPort.saveSoul(soulSaveRequestDto);
    }

    public void deleteSoul(Long id) {
        deleteSoulInboundPort.deleteSoul(id);
    }

    public void putSoul(SoulPutRequestDto soulPutRequestDto) {
        putSoulInboundPort.putSoul(soulPutRequestDto);
    }
}
