package com.strategy.enummodel;

public enum StageValueEnum {

    LOCATION_MIN(1),
    LOCATION_MAX(16),
    STEP_MIN(1),
    STEP_MAX(45);

    private final int value;

    StageValueEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
