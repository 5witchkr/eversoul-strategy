package com.strategy.eventmodel.souldeleteevent;

public class SoulCharacterTacticSoulDeleteEvent {
    private final Long id;

    public SoulCharacterTacticSoulDeleteEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
