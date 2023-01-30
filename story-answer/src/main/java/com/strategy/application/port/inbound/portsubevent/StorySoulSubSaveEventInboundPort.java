package com.strategy.application.port.inbound.portsubevent;

import com.strategy.eventmodel.soulsaveevent.SoulCharacterStorySoulSaveEvent;

public interface StorySoulSubSaveEventInboundPort {

    void saveSoul(SoulCharacterStorySoulSaveEvent soulCharacterStorySoulSaveEvent);
}
