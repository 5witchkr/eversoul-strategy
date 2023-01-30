package com.strategy.eventmodel.soulputevent;

public class SoulCharacterTacticSoulPutEvent {
    private final Long id;
    private final String name;

    private final String type;

    private final String tier;

    public SoulCharacterTacticSoulPutEvent(Long id, String name, String type, String tier) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.tier = tier;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getTier() {
        return tier;
    }
}
