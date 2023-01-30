package com.strategy.application.port.inbound.portsubevent;

import com.strategy.eventmodel.soulputevent.SoulCharacterStorySoulPutEvent;

public interface StorySoulSubPutEventInboundPort {

    void putSoul(SoulCharacterStorySoulPutEvent soulCharacterStorySoulPutEvent);
}
