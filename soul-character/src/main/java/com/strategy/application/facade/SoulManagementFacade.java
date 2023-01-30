package com.strategy.application.facade;

import com.strategy.application.port.inbound.DeleteSoulInboundPort;
import com.strategy.application.port.inbound.PutSoulInboundPort;
import com.strategy.application.port.inbound.SaveSoulInboundPort;
import com.strategy.application.port.inbound.SoulPubEventInboundPort;
import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SoulManagementFacade {

    private final SaveSoulInboundPort saveSoulInboundPort;
    private final PutSoulInboundPort putSoulInboundPort;
    private final DeleteSoulInboundPort deleteSoulInboundPort;
    private final SoulPubEventInboundPort soulPubEventInboundPort;

    public SoulManagementFacade(SaveSoulInboundPort saveSoulInboundPort, PutSoulInboundPort putSoulInboundPort, DeleteSoulInboundPort deleteSoulInboundPort, SoulPubEventInboundPort soulPubEventInboundPort) {
        this.saveSoulInboundPort = saveSoulInboundPort;
        this.putSoulInboundPort = putSoulInboundPort;
        this.deleteSoulInboundPort = deleteSoulInboundPort;
        this.soulPubEventInboundPort = soulPubEventInboundPort;
    }


    @Transactional
    public void saveSoul(SoulSaveRequestDto soulSaveRequestDto) {
        saveSoulInboundPort.saveSoul(soulSaveRequestDto);
        soulPubEventInboundPort.publishSaveEvent(soulSaveRequestDto);
    }

    @Transactional
    public void deleteSoul(Long id) {
        deleteSoulInboundPort.deleteSoul(id);
        soulPubEventInboundPort.publishDeleteEvent(id);
    }

    @Transactional
    public void putSoul(SoulPutRequestDto soulPutRequestDto) {
        putSoulInboundPort.putSoul(soulPutRequestDto);
        soulPubEventInboundPort.publishPutEvent(soulPutRequestDto);
    }
}
