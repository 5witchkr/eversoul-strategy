package com.strategy.enummodel;

public enum TacticPositionEnum {
    POSITION_1("기본"),
    POSITION_2("수비"),
    POSITION_3("저격"),
    POSITION_4("돌격");

    private final String value;

    TacticPositionEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
