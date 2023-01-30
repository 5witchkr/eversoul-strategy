package com.strategy.eventmodel.soulsaveevent;

public class SoulCharacterStorySoulSaveEvent {
    private final String name;

    private final String type;

    private final String tier;

    public SoulCharacterStorySoulSaveEvent(String name, String type, String tier) {
        this.name = name;
        this.type = type;
        this.tier = tier;
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