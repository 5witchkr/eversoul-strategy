package com.strategy.enummodel;

public enum StoryEpisodeEnum {
    MIN_EPISODE(0),
    MAX_EPISODE(30);

    private final int value;


    StoryEpisodeEnum(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
