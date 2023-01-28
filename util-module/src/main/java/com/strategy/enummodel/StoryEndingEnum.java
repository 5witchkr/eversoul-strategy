package com.strategy.enummodel;

public enum StoryEndingEnum {
    TRUE_END("TRUE"),
    BAD_END("BAD");

    private final String value;

    StoryEndingEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
