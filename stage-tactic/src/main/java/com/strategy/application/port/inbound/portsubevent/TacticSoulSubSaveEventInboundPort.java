package com.strategy.application.port.inbound.portsubevent;

import com.strategy.eventmodel.soulsaveevent.SoulCharacterTacticSoulSaveEvent;

public interface TacticSoulSubSaveEventInboundPort {

    void saveSoul(SoulCharacterTacticSoulSaveEvent soulCharacterTacticSoulSaveEvent);
}
