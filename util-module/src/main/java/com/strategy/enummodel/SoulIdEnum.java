package com.strategy.enummodel;

public enum SoulIdEnum {
    SOUL_ID_START(1L),
    SOUL_ID_END(33L);

    private final Long value;

    SoulIdEnum(Long value) {
        this.value = value;
    }

    public Long getValue(){
        return value;
    }
}
