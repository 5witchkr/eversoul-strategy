package com.strategy.application.port.inbound.portsubevent;

import com.strategy.eventmodel.souldeleteevent.SoulCharacterStorySoulDeleteEvent;

public interface StorySoulSubDeleteEventInboundPort {

    void deleteSoul(SoulCharacterStorySoulDeleteEvent soulCharacterStorySoulDeleteEvent);
}
