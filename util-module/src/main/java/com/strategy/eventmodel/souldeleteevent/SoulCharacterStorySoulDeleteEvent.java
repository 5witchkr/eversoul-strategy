package com.strategy.eventmodel.souldeleteevent;

public class SoulCharacterStorySoulDeleteEvent {
    private final Long id;

    public SoulCharacterStorySoulDeleteEvent(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
