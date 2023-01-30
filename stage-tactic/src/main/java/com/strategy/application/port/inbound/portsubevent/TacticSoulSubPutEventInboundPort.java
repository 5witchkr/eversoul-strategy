package com.strategy.application.port.inbound.portsubevent;

import com.strategy.eventmodel.soulputevent.SoulCharacterTacticSoulPutEvent;

public interface TacticSoulSubPutEventInboundPort {

    void putSoul(SoulCharacterTacticSoulPutEvent soulCharacterTacticSoulPutEvent);
}
