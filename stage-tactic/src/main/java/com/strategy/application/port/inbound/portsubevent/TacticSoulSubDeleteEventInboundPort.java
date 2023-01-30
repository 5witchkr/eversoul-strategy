package com.strategy.application.port.inbound.portsubevent;


import com.strategy.eventmodel.souldeleteevent.SoulCharacterTacticSoulDeleteEvent;

public interface TacticSoulSubDeleteEventInboundPort {

    void deleteSoul(SoulCharacterTacticSoulDeleteEvent soulCharacterTacticSoulDeleteEvent);
}
